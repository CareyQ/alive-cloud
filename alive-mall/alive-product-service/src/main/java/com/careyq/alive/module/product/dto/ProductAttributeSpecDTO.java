package com.careyq.alive.module.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 商品属性规格 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品属性规格 DTO")
public class ProductAttributeSpecDTO {

    @Schema(description = "主键")
    private Long id;

    @NotBlank(message = "规格名称不能为空")
    @Schema(description = "规格名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

}