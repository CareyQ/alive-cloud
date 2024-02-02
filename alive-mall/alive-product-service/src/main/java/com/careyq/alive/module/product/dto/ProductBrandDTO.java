package com.careyq.alive.module.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 商品品牌 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品品牌 DTO")
public class ProductBrandDTO {

    @Schema(description = "主键")
    private Long id;

    @NotBlank(message = "品牌名称不能为空")
    @Schema(description = "品牌名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotNull(message = "排序不能为空")
    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer sort;

    @NotBlank(message = "品牌 logo不能为空")
    @Schema(description = "品牌 logo", requiredMode = Schema.RequiredMode.REQUIRED)
    private String logo;

    @Schema(description = "品牌描述")
    private String description;

    @NotNull(message = "状态不能为空")
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer status;

}