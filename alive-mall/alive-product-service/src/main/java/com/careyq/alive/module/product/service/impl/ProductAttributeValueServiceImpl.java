package com.careyq.alive.module.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.module.product.dto.ProductAttributeValueDTO;
import com.careyq.alive.module.product.dto.ProductParamDTO;
import com.careyq.alive.module.product.entity.ProductAttributeValue;
import com.careyq.alive.module.product.mapper.ProductAttributeValueMapper;
import com.careyq.alive.module.product.service.ProductAttributeValueService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        this.saveOrUpdate(attributeValue);
        return attributeValue.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveProductParam(Long productId, List<ProductParamDTO> param) {
        List<ProductAttributeValue> attributeValues = CollUtils.convertList(param, e -> {
            ProductAttributeValue attributeValue = new ProductAttributeValue();
            attributeValue.setAttributeId(e.getAttributeId())
                    .setProductId(productId)
                    .setValue(e.getValue());
            return attributeValue;
        });
        this.saveBatch(attributeValues);
    }
}
