package com.careyq.alive.module.product.dto;

import com.careyq.alive.module.product.entity.ProductSku;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品 SKU 信息 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品 SKU 信息 DTO")
public class ProductSkuDTO {

    @Schema(description = "编号")
    private Long id;

    @Schema(description = "商品编号")
    private Long productId;

    @Schema(description = "SKU 编号")
    private String snCode;

    @Valid
    @NotEmpty(message = "SKU 规格信息不能为空")
    @Schema(description = "SKU 规格信息", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<ProductSku.Spec> spec;

    @NotNull(message = "价格不能为空")
    @Schema(description = "价格", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal price;

    @NotNull(message = "库存不能为空")
    @Schema(description = "库存", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer stock;

    @NotBlank(message = "SKU 图片不能为空")
    @Schema(description = "SKU 图片", requiredMode = Schema.RequiredMode.REQUIRED)
    private String pic;

    @Schema(description = "商品重量，千克")
    private BigDecimal weight;

    @Schema(description = "商品体积，立方米")
    private BigDecimal volume;

}