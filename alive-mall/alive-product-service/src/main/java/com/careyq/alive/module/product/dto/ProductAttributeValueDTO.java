package com.careyq.alive.module.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 商品属性值 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品属性值 DTO")
public class ProductAttributeValueDTO {

    @Schema(description = "主键")
    private Long id;

    @NotNull(message = "属性 ID不能为空")
    @Schema(description = "属性 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long attributeId;

    @NotBlank(message = "属性值不能为空")
    @Schema(description = "属性值", requiredMode = Schema.RequiredMode.REQUIRED)
    private String value;

}