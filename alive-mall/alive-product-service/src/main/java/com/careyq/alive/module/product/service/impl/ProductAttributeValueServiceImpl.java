package com.careyq.alive.module.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.module.product.dto.ProductAttributeValueDTO;
import com.careyq.alive.module.product.entity.ProductAttributeValue;
import com.careyq.alive.module.product.mapper.ProductAttributeValueMapper;
import com.careyq.alive.module.product.service.ProductAttributeValueService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 商品属性值 服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class ProductAttributeValueServiceImpl extends ServiceImpl<ProductAttributeValueMapper, ProductAttributeValue> implements ProductAttributeValueService {

    @Override
    public Long saveAttributeValue(ProductAttributeValueDTO dto) {
        ProductAttributeValue attributeValue = new ProductAttributeValue();
        attributeValue.setAttributeId(dto.getAttributeId())
                .setValue(dto.getValue())
                .setId(dto.getId());
        this.save(attributeValue);
        return attributeValue.getId();
    }
}
