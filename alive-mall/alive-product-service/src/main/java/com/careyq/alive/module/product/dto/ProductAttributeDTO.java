package com.careyq.alive.module.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 商品属性 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品属性 DTO")
public class ProductAttributeDTO {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "所属分组")
    private Long groupId;

    @NotBlank(message = "属性名称不能为空")
    @Schema(description = "属性名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotNull(message = "属性选择类型，0唯一 1单选 2多选不能为空")
    @Schema(description = "属性选择类型，0唯一 1单选 2多选", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer selectType;

    @NotNull(message = "属性录入方式，0手工录入 1从列表中选取不能为空")
    @Schema(description = "属性录入方式，0手工录入 1从列表中选取", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer inputType;

    @Schema(description = "可选值列表，以逗号隔开")
    private String inputList;

    @NotNull(message = "排序不能为空")
    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer sort;

    @NotNull(message = "分类筛选样式，1普通 1颜色不能为空")
    @Schema(description = "分类筛选样式，1普通 1颜色", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer filterType;

    @NotNull(message = "检索类型，0不需要进行检索 1关键字检索 2范围检索不能为空")
    @Schema(description = "检索类型，0不需要进行检索 1关键字检索 2范围检索", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer searchType;

    @NotNull(message = "相同属性产品是否关联，0不关联 1关联不能为空")
    @Schema(description = "相同属性产品是否关联，0不关联 1关联", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer relatedStatus;

    @NotNull(message = "支持新增不能为空")
    @Schema(description = "支持新增", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean addition;

}