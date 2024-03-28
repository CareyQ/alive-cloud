package com.careyq.alive.module.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.module.product.convert.ProductAttributeConvert;
import com.careyq.alive.module.product.dto.ProductAttributePageDTO;
import com.careyq.alive.module.product.entity.ProductAttribute;
import com.careyq.alive.module.product.mapper.ProductAttributeMapper;
import com.careyq.alive.module.product.service.ProductAttributeService;
import com.careyq.alive.module.product.vo.ProductAttributePageVO;
import com.careyq.alive.module.product.vo.ProductAttributeVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.careyq.alive.module.product.constants.ProductResultCode.ATTRIBUTE_NOT_EXISTS;

/**
 * 商品属性 服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttribute> implements ProductAttributeService {

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
        return page.convert(ProductAttributeConvert.INSTANCE::convertToPageVo);
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
}
