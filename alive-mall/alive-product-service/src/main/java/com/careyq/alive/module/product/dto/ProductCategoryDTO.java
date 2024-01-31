package com.careyq.alive.module.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 商品分类 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品分类 DTO")
public class ProductCategoryDTO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @NotNull(message = "父级分类不能为空")
    @Schema(description = "父级分类 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long parentId;

    @NotBlank(message = "分类名称不能为空")
    @Schema(description = "分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotNull(message = "排序不能为空")
    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer sort;

    @Schema(description = "图标")
    private String icon;

    @NotNull(message = "状态不能为空")
    @Schema(description = "状态，0停用 1正常", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer status;

}