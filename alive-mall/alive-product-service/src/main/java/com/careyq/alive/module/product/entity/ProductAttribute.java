package com.careyq.alive.module.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.careyq.alive.core.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品属性
 *
 * @author CareyQ
 */
@Data
@NoArgsConstructor
@TableName("product_attribute")
public class ProductAttribute extends BaseEntity {

    /**
     * 属性名称
     */
    private String name;

    public ProductAttribute(String name) {
        this.name = name;
    }
}