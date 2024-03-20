package com.careyq.alive.module.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.careyq.alive.core.domain.BaseEntity;
import lombok.Data;

/**
 * 商品属性值
 *
 * @author CareyQ
 */
@Data
@TableName("product_attribute_value")
public class ProductAttributeValue extends BaseEntity {

    /**
     * 属性 ID 
     */
    private Long attributeId;

    /**
     * 商品 ID
     */
    private Long productId;

    /**
     * 属性值 
     */
    private String value;

    /**
     * 排序 
     */
    private Integer sort;

}