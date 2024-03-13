package com.careyq.alive.module.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.careyq.alive.core.domain.BaseEntity;
import lombok.Data;

/**
 * 商品属性
 *
 * @author CareyQ
 */
@Data
@TableName("product_attribute")
public class ProductAttribute extends BaseEntity {

    /**
     * 所属分组 
     */
    private Long groupId;

    /**
     * 属性类型，0规格 1参数 
     */
    private Integer type;

    /**
     * 属性名称 
     */
    private String name;

    /**
     * 属性选择类型，0唯一 1单选 2多选 
     */
    private Integer selectType;

    /**
     * 属性录入方式，0手工录入 1从列表中选取 
     */
    private Integer inputType;

    /**
     * 可选值列表，以逗号隔开 
     */
    private String inputList;

    /**
     * 排序 
     */
    private Integer sort;

    /**
     * 分类筛选样式，1普通 1颜色 
     */
    private Integer filterType;

    /**
     * 检索类型，0不需要进行检索 1关键字检索 2范围检索 
     */
    private Integer searchType;

    /**
     * 相同属性产品是否关联，0不关联 1关联 
     */
    private Integer relatedStatus;

    /**
     * 支持新增 
     */
    private Boolean addition;

}