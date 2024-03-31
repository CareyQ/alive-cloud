package com.careyq.alive.module.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品信息 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品信息 DTO")
public class ProductDTO {

    @Schema(description = "编号")
    private Long id;

    @NotNull(message = "所属分类不能为空")
    @Schema(description = "所属分类", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long categoryId;

    @NotNull(message = "所属品牌不能为空")
    @Schema(description = "所属品牌", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long brandId;

    @NotBlank(message = "商品名称不能为空")
    @Schema(description = "商品名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotEmpty(message = "商品轮播图片不能为空")
    @Schema(description = "商品轮播图片", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> slidePic;

    @NotNull(message = "上架状态不能为空")
    @Schema(description = "状态，0下架 1上架", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer status;

    @NotNull(message = "排序不能为空")
    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer sort;

    @Schema(description = "价格")
    private BigDecimal price;

    @Schema(description = "库存")
    private Integer stock;

    @NotBlank(message = "单位不能为空")
    @Schema(description = "单位", requiredMode = Schema.RequiredMode.REQUIRED)
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

    @Schema(description = "是否新品")
    private Boolean newStatus;

    @Schema(description = "是否推荐")
    private Boolean recommendStatus;

    @Schema(description = "服务保障")
    private List<Integer> service;

    @Schema(description = "副标题", requiredMode = Schema.RequiredMode.REQUIRED)
    private String subTitle;

    @Schema(description = "关键字", requiredMode = Schema.RequiredMode.REQUIRED)
    private String keyword;

    @Valid
    @Schema(description = "商品 SKU 信息")
    private List<ProductSkuDTO> skus;
}