package com.careyq.alive.module.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 商品参数 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品参数 DTO")
public class ProductParamDTO {

    @Schema(description = "编号")
    private Long id;

    @Schema(description = "商品编号")
    private Long productId;

    @NotNull(message = "所属品牌不能为空")
    @Schema(description = "属性编号")
    private Long attributeId;

    @NotBlank(message = "属性值不能为空")
    @Schema(description = "属性值", requiredMode = Schema.RequiredMode.REQUIRED)
    private String value;
}