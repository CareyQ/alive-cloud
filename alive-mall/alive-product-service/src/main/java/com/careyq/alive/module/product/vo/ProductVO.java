package com.careyq.alive.module.product.vo;

import com.careyq.alive.module.product.dto.ProductSkuDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品信息 VO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品信息 VO")
public class ProductVO {

    @Schema(description = "编号")
    private Long id;

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

    @Schema(description = "价格")
    private BigDecimal price;

    @Schema(description = "市场价")
    private BigDecimal marketPrice;

    @Schema(description = "库存")
    private Integer stock;

    @Schema(description = "单位")
    private String unit;

    @Schema(description = "商品详情")
    private String detailHtml;

    @Schema(description = "移动端商品详情")
    private String detailMobileHtml;

    @Schema(description = "赠送积分")
    private Integer giftPoint;

    @Schema(description = "赠送成长值")
    private Integer giftGrowth;

    @Schema(description = "限制使用的积分数")
    private Integer usePointLimit;

    @Schema(description = "副标题")
    private String subTitle;

    @Schema(description = "关键字")
    private String keyword;

    @Schema(description = "简介")
    private String intro;

    @Schema(description = "商品 SKU 信息")
    private List<ProductSkuDTO> skus;
}