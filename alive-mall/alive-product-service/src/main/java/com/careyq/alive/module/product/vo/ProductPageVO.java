package com.careyq.alive.module.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;

/**
 * 商品信息分页 VO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品信息分页 VO")
public class ProductPageVO {

    @Schema(description = "所属分类")
    private Long categoryId;

    @Schema(description = "所属品牌")
    private Long brandId;

    @Schema(description = "商品编号")
    private String snCode;

    @Schema(description = "商品名称")
    private String name;

    @Schema(description = "商品封面图片")
    private String pic;

    @Schema(description = "状态，0下架 1上架")
    private Integer status;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "销量")
    private Integer salesVolume;

    @Schema(description = "价格")
    private BigDecimal price;

    @Schema(description = "市场价")
    private BigDecimal marketPrice;

    @Schema(description = "库存")
    private Integer stock;

    @Schema(description = "单位")
    private String unit;

}