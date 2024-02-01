package com.careyq.alive.module.product.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.careyq.alive.core.domain.BaseEntity;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 商品品牌
 *
 * @author CareyQ
 */
@Data
@TableName("product_brand")
public class ProductBrand extends BaseEntity {

    /**
     * 品牌名称 
     */
    private String name;

    /**
     * 排序 
     */
    private Integer sort;

    /**
     * 品牌 logo 
     */
    private String logo;

    /**
     * 品牌描述 
     */
    private String description;

    /**
     * 状态 
     */
    private Integer status;


}