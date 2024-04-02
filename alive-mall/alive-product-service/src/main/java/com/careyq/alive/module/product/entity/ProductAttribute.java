package com.careyq.alive.module.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.careyq.alive.core.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 商品属性
 *
 * @author CareyQ
 */
@Data
@NoArgsConstructor
@TableName(value = "product_attribute", autoResultMap = true)
public class ProductAttribute extends BaseEntity {

    /**
     * 属性名称
     */
    private String name;

    /**
     * 商品编号
     */
    private Long productId;

    /**
     * 属性值
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> value;

    public ProductAttribute(String name) {
        this.name = name;
    }
}