package com.careyq.alive.module.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 商品属性分组 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品属性分组 DTO")
public class ProductAttributeGroupDTO {

    @Schema(description = "主键")
    private Long id;

    @NotNull(message = "分类 ID不能为空")
    @Schema(description = "分类 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long categoryId;

    @NotBlank(message = "属性分组名称不能为空")
    @Schema(description = "属性分组名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotNull(message = "排序不能为空")
    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer sort;

}