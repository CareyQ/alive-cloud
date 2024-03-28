package com.careyq.alive.module.product.service;

import com.careyq.alive.module.product.dto.ProductAttributeValueDTO;
import com.careyq.alive.module.product.entity.ProductAttributeValue;
import com.careyq.alive.mybatis.core.service.IServiceX;

/**
 * 商品属性值 服务
 *
 * @author CareyQ
 */
public interface ProductAttributeValueService extends IServiceX<ProductAttributeValue> {

    /**
     * 保存商品属性值
     *
     * @param dto 商品属性值
     * @return 属性值编号
     */
    Long saveAttributeValue(ProductAttributeValueDTO dto);

}
