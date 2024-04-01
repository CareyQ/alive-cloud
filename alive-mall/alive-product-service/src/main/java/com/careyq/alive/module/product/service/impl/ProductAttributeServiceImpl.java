package com.careyq.alive.module.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.module.product.convert.ProductAttributeConvert;
import com.careyq.alive.module.product.dto.ProductAttributePageDTO;
import com.careyq.alive.module.product.entity.Product;
import com.careyq.alive.module.product.entity.ProductAttribute;
import com.careyq.alive.module.product.entity.ProductSku;
import com.careyq.alive.module.product.mapper.ProductAttributeMapper;
import com.careyq.alive.module.product.mapper.ProductMapper;
import com.careyq.alive.module.product.service.ProductAttributeService;
import com.careyq.alive.module.product.vo.ProductAttributePageVO;
import com.careyq.alive.module.product.vo.ProductAttributeVO;
import com.careyq.alive.mybatis.core.query.LambdaQueryWrapperX;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.careyq.alive.module.product.constants.ProductResultCode.ATTRIBUTE_NOT_EXISTS;

/**
 * 商品属性 服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttribute> implements ProductAttributeService {

    private final ProductMapper productMapper;

    @Override
    public Long saveAttribute(String name) {
        ProductAttribute attribute = new ProductAttribute(name);
        this.save(attribute);
        return attribute.getId();
    }

    @Override
    public IPage<ProductAttributePageVO> getAttributePage(ProductAttributePageDTO dto) {
        IPage<ProductAttribute> page = this.lambdaQueryX()
                .likeIfPresent(ProductAttribute::getName, dto.getName())
                .orderByDesc(ProductAttribute::getId)
                .page(new Page<>(dto.getCurrent(), dto.getSize()));
        if (page.getRecords().isEmpty()) {
            return new Page<>();
        }
        Map<Long, String> productMap = MapUtil.newHashMap();
        Set<Long> productIds = CollUtils.convertSet(page.getRecords(), ProductAttribute::getProductId);
        if (CollUtils.isNotEmpty(productIds)) {
            List<Product> products = productMapper.selectList(new LambdaQueryWrapperX<Product>()
                    .select(Product::getId, Product::getName)
                    .in(Product::getId, productIds));
            productMap = CollUtils.convertMap(products, Product::getId, Product::getName);
        }
        Map<Long, String> finalProductMap = productMap;
        return page.convert(e -> {
            ProductAttributePageVO vo = ProductAttributeConvert.INSTANCE.convertToPageVo(e);
            vo.setProductName(finalProductMap.get(e.getProductId()));
            return vo;
        });
    }

    @Override
    public ProductAttributeVO getAttributeDetail(Long id) {
        ProductAttribute data = this.checkDataExists(id);
        return BeanUtil.copyProperties(data, ProductAttributeVO.class);
    }

    @Override
    public void delAttribute(Long id) {
        this.checkDataExists(id);
        this.removeById(id);
    }

    /**
     * 校验商品属性是否存在
     *
     * @param id 编号
     * @return 商品属性
     */
    private ProductAttribute checkDataExists(Long id) {
        if (id == null) {
            return null;
        }
        ProductAttribute data = this.getById(id);
        if (data == null) {
            throw new CustomException(ATTRIBUTE_NOT_EXISTS);
        }
        return data;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAttributeValue(Long productId, List<List<ProductSku.Spec>> specs) {
        List<ProductAttribute> attributes = new ArrayList<>();
        for (List<ProductSku.Spec> spec : specs) {
            List<String> value = CollUtils.convertList(spec, ProductSku.Spec::getValue);
            ProductAttribute attribute = new ProductAttribute();
            attribute.setId(spec.getFirst().getAttributeId());
            attribute.setProductId(productId);
            attribute.setValue(value);
            attributes.add(attribute);
        }
        this.updateBatchById(attributes);
    }
}
