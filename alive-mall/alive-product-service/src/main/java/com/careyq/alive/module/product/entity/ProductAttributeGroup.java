package com.careyq.alive.module.product.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.careyq.alive.core.domain.BaseEntity;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 商品属性分组
 *
 * @author CareyQ
 */
@Data
@TableName("product_attribute_group")
public class ProductAttributeGroup extends BaseEntity {

    /**
     * 分类 ID 
     */
    private Long categoryId;

    /**
     * 属性分组名称 
     */
    private String name;

    /**
     * 排序 
     */
    private Integer sort;


}