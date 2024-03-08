package com.careyq.alive.module.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.module.product.convert.ProductConvert;
import com.careyq.alive.module.product.dto.ProductDTO;
import com.careyq.alive.module.product.dto.ProductPageDTO;
import com.careyq.alive.module.product.entity.Product;
import com.careyq.alive.module.product.mapper.ProductMapper;
import com.careyq.alive.module.product.service.ProductService;
import com.careyq.alive.module.product.vo.ProductPageVO;
import com.careyq.alive.module.product.vo.ProductVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品信息 服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    private final ProductMapper Mapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long save(ProductDTO dto) {
        boolean exists = this.lambdaQueryX()
                .neIfPresent(Product::getId, dto.getId())
                // TODO
                .exists();
        if (exists) {
            throw new CustomException();
        }
        if (dto.getId() != null) {
            this.checkDataExists(dto.getId());
        }
        Product product  = BeanUtil.copyProperties(dto, Product.class);
        this.saveOrUpdate(product);
        return product.getId();
    }

    @Override
    public IPage<ProductPageVO> getPage(ProductPageDTO dto) {
        IPage<Product> page = this.lambdaQueryX()
            .eqIfPresent(Product::getCategoryId, dto.getCategoryId())
            .eqIfPresent(Product::getBrandId, dto.getBrandId())
            .eqIfPresent(Product::getSnCode, dto.getSnCode())
            .likeIfPresent(Product::getName, dto.getName())
            .eqIfPresent(Product::getStatus, dto.getStatus())
            .orderByDesc(Product::getId)
            .page(new Page<>(dto.getCurrent(), dto.getSize()));
        if (page.getRecords().isEmpty()) {
            return new Page<>();
        }
        return page.convert(ProductConvert.INSTANCE::convert);
    }

    @Override
    public ProductVO getDetail(Long id) {
        Product data = this.checkDataExists(id);
        return BeanUtil.copyProperties(data, ProductVO.class);
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

}
