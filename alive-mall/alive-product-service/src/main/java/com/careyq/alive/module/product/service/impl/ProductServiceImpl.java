package com.careyq.alive.module.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.module.product.convert.ProductConvert;
import com.careyq.alive.module.product.dto.ProductDTO;
import com.careyq.alive.module.product.dto.ProductPageDTO;
import com.careyq.alive.module.product.dto.ProductSkuDTO;
import com.careyq.alive.module.product.entity.Product;
import com.careyq.alive.module.product.entity.ProductBrand;
import com.careyq.alive.module.product.entity.ProductCategory;
import com.careyq.alive.module.product.entity.ProductSku;
import com.careyq.alive.module.product.enums.ProductStatusEnum;
import com.careyq.alive.module.product.mapper.ProductMapper;
import com.careyq.alive.module.product.service.ProductBrandService;
import com.careyq.alive.module.product.service.ProductCategoryService;
import com.careyq.alive.module.product.service.ProductService;
import com.careyq.alive.module.product.service.ProductSkuService;
import com.careyq.alive.module.product.vo.ProductPageVO;
import com.careyq.alive.module.product.vo.ProductVO;
import com.careyq.alive.search.api.EsProductApi;
import com.careyq.alive.search.dto.EsProductDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.careyq.alive.module.product.constants.ProductResultCode.*;

/**
 * 商品信息 服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    private final EsProductApi esProductApi;

    private final ProductCategoryService categoryService;
    private final ProductBrandService brandService;
    private final ProductSkuService skuService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createProduct(ProductDTO dto) {
        this.validateProduct(dto);
        Product product = BeanUtil.copyProperties(dto, Product.class);
        initSpuFormSku(product, dto.getSkus());

        this.save(product);
        skuService.createProductSku(product, dto.getSkus());
        this.up(product);
        return product.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateProduct(ProductDTO dto) {
        this.validateProduct(dto);
        Product product = BeanUtil.copyProperties(dto, Product.class);
        initSpuFormSku(product, dto.getSkus());

        this.updateById(product);
        skuService.updateProductSku(product, dto.getSkus());
        this.up(product);
        return product.getId();
    }

    /**
     * 校验商品信息
     *
     * @param dto 商品信息
     */
    private void validateProduct(ProductDTO dto) {
        // 校验分类/品牌
        categoryService.validateCategory(dto.getCategoryId());
        brandService.validateBrand(dto.getBrandId());
        // 校验 SKU
        skuService.validateSkus(dto.getSkus());
    }

    /**
     * 初始化商品基本数据
     *
     * @param product 商品
     * @param skus    sku 信息
     */
    private static void initSpuFormSku(Product product, List<ProductSkuDTO> skus) {
        if (product.getPrice() == null) {
            product.setPrice(CollUtils.getMinValue(skus, ProductSkuDTO::getPrice));
        }
        product.setStock(CollUtils.getSumValue(skus, ProductSkuDTO::getStock, Integer::sum));
    }

    @Override
    public IPage<ProductPageVO> getPage(ProductPageDTO dto) {
        Long productId = null;
        if (StrUtil.isNotEmpty(dto.getSnCode())) {
            ProductSku sku = skuService.lambdaQuery()
                    .eq(ProductSku::getSnCode, dto.getSnCode())
                    .one();
            productId = Optional.ofNullable(sku).orElseGet(ProductSku::new).getProductId();
        }
        IPage<Product> page;
        if (productId != null) {
            page = this.lambdaQueryX()
                    .eq(Product::getId, productId)
                    .page(new Page<>(dto.getCurrent(), dto.getSize()));
        } else {
            page = this.lambdaQueryX()
                    .eqIfPresent(Product::getCategoryId, dto.getCategoryId())
                    .eqIfPresent(Product::getBrandId, dto.getBrandId())
                    .likeIfPresent(Product::getName, dto.getName())
                    .eqIfPresent(Product::getStatus, dto.getStatus())
                    .orderByDesc(Product::getId)
                    .page(new Page<>(dto.getCurrent(), dto.getSize()));
        }

        if (page.getRecords().isEmpty()) {
            return new Page<>();
        }
        List<Long> categoryId = CollUtils.convertList(page.getRecords(), Product::getCategoryId);
        Map<Long, String> categoryMap = categoryService.getCategoryNameMap(categoryId);

        List<Long> brandId = CollUtils.convertList(page.getRecords(), Product::getBrandId);
        Map<Long, String> brandMap = brandService.getBrandNameMap(brandId);
        return page.convert(e -> {
            ProductPageVO vo = ProductConvert.INSTANCE.convert(e);
            vo.setCategoryName(categoryMap.get(e.getCategoryId()));
            vo.setBrandName(brandMap.get(e.getBrandId()));
            return vo;
        });
    }

    @Override
    public ProductVO getDetail(Long id) {
        Product data = this.checkDataExists(id);
        ProductVO res = BeanUtil.copyProperties(data, ProductVO.class);
        List<ProductSku> skuList = skuService.lambdaQueryX()
                .eq(ProductSku::getProductId, id)
                .list();
        res.setSkus(CollUtils.convertList(skuList, ProductConvert.INSTANCE::skuConvert));
        return res;
    }

    @Override
    public void del(Long id) {
        this.checkDataExists(id);
        this.removeById(id);
    }

    /**
     * 校验商品信息是否存在
     *
     * @param id 编号
     * @return 商品信息
     */
    private Product checkDataExists(Long id) {
        if (id == null) {
            return null;
        }
        Product data = this.getById(id);
        if (data == null) {
            throw new CustomException(PRODUCT_NOT_EXISTS);
        }
        return data;
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Product product = this.checkDataExists(id);
        boolean isDown = ProductStatusEnum.DOWN.getCode().equals(status);
        if (product.getStatus().equals(status)) {
            throw new CustomException(PRODUCT_STATUS_ALREADY, isDown ? "下架" : "上架");
        }
        this.lambdaUpdate()
                .set(Product::getStatus, status)
                .eq(Product::getId, id)
                .update();
        this.up(product);
    }

    private void up(Product product) {
        if (!ProductStatusEnum.UP.getCode().equals(product.getStatus())) {
            return;
        }
        List<EsProductDTO.Attrs> attrs = new ArrayList<>();
        ProductBrand brand = brandService.getById(product.getBrandId());
        ProductCategory category = categoryService.getById(product.getCategoryId());

        EsProductDTO dto = new EsProductDTO();
        dto.setProductId(product.getId())
                .setName(product.getName())
                .setPrice(product.getPrice())
                .setPic(product.getSlidePic().getFirst())
                .setSalesVolume(product.getSalesVolume())
                .setHasStock(product.getStock() > 0)
                .setBrandId(product.getBrandId())
                .setBrandName(brand.getName())
                .setBrandLogo(brand.getLogo())
                .setCategoryId(product.getCategoryId())
                .setCategoryName(category.getName())
                .setAttrs(attrs);
        // todo 接口幂等、重试机制
        boolean res = esProductApi.upProduct(dto);
        if (res) {
            this.lambdaUpdate()
                    .set(Product::getStatus, ProductStatusEnum.UP.getCode())
                    .eq(Product::getId, product.getId())
                    .update();
            return;
        }
        throw new CustomException(PRODUCT_UP_FAIL);
    }
}
