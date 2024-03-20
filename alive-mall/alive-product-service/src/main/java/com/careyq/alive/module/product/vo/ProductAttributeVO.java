package com.careyq.alive.module.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 商品属性 VO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品属性 VO")
public class ProductAttributeVO {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "所属分组")
    private Long groupId;

    @Schema(description = "属性类型，0规格 1参数")
    private Integer type;

    @Schema(description = "属性名称")
    private String name;

    @Schema(description = "属性值")
    private String value;

    @Schema(description = "属性选择类型，0唯一 1单选 2多选")
    private Integer selectType;

    @Schema(description = "属性录入方式，0手工录入 1从列表中选取")
    private Integer inputType;

    @Schema(description = "可选值列表，以逗号隔开")
    private String inputList;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "分类筛选样式，1普通 1颜色")
    private Integer filterType;

    @Schema(description = "检索类型，0不需要进行检索 1关键字检索 2范围检索")
    private Integer searchType;

    @Schema(description = "相同属性产品是否关联，0不关联 1关联")
    private Integer relatedStatus;

    @Schema(description = "支持新增")
    private Boolean addition;

}