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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 商品属性值 服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class ProductAttributeValueServiceImpl extends ServiceImpl<ProductAttributeValueMapper, ProductAttributeValue> implements ProductAttributeValueService {

    private final ProductAttributeValueMapper attributeValueMapper;

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
    public void createProductParam(Long productId, List<ProductParamDTO> param) {
        List<ProductAttributeValue> attributeValues = CollUtils.convertList(param, e -> {
            ProductAttributeValue attributeValue = new ProductAttributeValue();
            attributeValue.setAttributeId(e.getAttributeId())
                    .setProductId(productId)
                    .setValue(e.getValue());
            return attributeValue;
        });
        this.saveBatch(attributeValues);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProductParam(Long productId, List<ProductParamDTO> param) {
        List<ProductAttributeValue> attributeValues = attributeValueMapper.selectExistAttribute(productId);
        Map<Long, ProductAttributeValue> valueMap = CollUtils.convertMap(attributeValues, ProductAttributeValue::getAttributeId, Function.identity());

        List<ProductAttributeValue> update = new ArrayList<>();
        List<ProductAttributeValue> create = new ArrayList<>();
        for (ProductParamDTO item : param) {
            ProductAttributeValue attributeValue = new ProductAttributeValue();
            attributeValue.setAttributeId(item.getAttributeId())
                    .setProductId(productId)
                    .setValue(item.getValue());
            ProductAttributeValue value = valueMap.remove(item.getAttributeId());
            if (value != null) {
                attributeValue.setId(value.getId());
                update.add(attributeValue);
                continue;
            }
            create.add(attributeValue);
        }

        if (!update.isEmpty()) {
            this.updateBatchById(update);
        }
        if (!create.isEmpty()) {
            this.saveBatch(create);
        }
        if (!valueMap.values().isEmpty()) {
            this.removeBatchByIds(valueMap.values());
        }
    }
}
