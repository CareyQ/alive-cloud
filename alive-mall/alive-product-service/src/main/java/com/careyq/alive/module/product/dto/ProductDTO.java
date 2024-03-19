package com.careyq.alive.module.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

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

    @NotNull(message = "属性分组不能为空")
    @Schema(description = "属性分组", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long attributeGroupId;

    @NotBlank(message = "商品编号不能为空")
    @Schema(description = "商品编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String snCode;

    @NotBlank(message = "商品名称不能为空")
    @Schema(description = "商品名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = "商品封面图片不能为空")
    @Schema(description = "商品封面图片", requiredMode = Schema.RequiredMode.REQUIRED)
    private String pic;

    @NotNull(message = "上架状态不能为空")
    @Schema(description = "状态，0下架 1上架", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer status;

    @NotNull(message = "排序不能为空")
    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer sort;

    @Schema(description = "价格")
    private BigDecimal price;

    @Schema(description = "市场价")
    private BigDecimal marketPrice;

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

    @NotBlank(message = "副标题不能为空")
    @Schema(description = "副标题", requiredMode = Schema.RequiredMode.REQUIRED)
    private String subTitle;

    @NotBlank(message = "关键字不能为空")
    @Schema(description = "关键字", requiredMode = Schema.RequiredMode.REQUIRED)
    private String keyword;

    @Schema(description = "简介")
    private String intro;

}