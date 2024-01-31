package com.careyq.alive.module.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.careyq.alive.core.domain.BaseEntity;
import lombok.Data;

/**
 * 商品分类
 *
 * @author CareyQ
 */
@Data
@TableName("product_category")
public class ProductCategory extends BaseEntity {

    /**
     * 父分类编号 - 根分类
     */
    public static final Long PARENT_ID_NULL = 0L;
    /**
     * 限定分类层级
     */
    public static final int CATEGORY_LEVEL = 2;

    /**
     * 父级分类 ID 
     */
    private Long parentId;

    /**
     * 分类名称 
     */
    private String name;

    /**
     * 排序 
     */
    private Integer sort;

    /**
     * 图标 
     */
    private String icon;

    /**
     * 状态，0停用 1正常 
     */
    private Integer status;

}