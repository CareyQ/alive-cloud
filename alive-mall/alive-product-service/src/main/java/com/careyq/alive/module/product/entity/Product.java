package com.careyq.alive.module.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.careyq.alive.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品信息
 *
 * @author CareyQ
 */
@Data
@TableName("product")
public class Product extends BaseEntity {

    /**
     * 所属分类 
     */
    private Long categoryId;

    /**
     * 所属品牌 
     */
    private Long brandId;

    /**
     * 商品编号 
     */
    private String snCode;

    /**
     * 商品名称 
     */
    private String name;

    /**
     * 商品封面图片 
     */
    private String pic;

    /**
     * 状态，0下架 1上架 
     */
    private Integer status;

    /**
     * 排序 
     */
    private Integer sort;

    /**
     * 销量 
     */
    private Integer salesVolume;

    /**
     * 价格 
     */
    private BigDecimal price;

    /**
     * 市场价 
     */
    private BigDecimal marketPrice;

    /**
     * 库存 
     */
    private Integer stock;

    /**
     * 单位 
     */
    private String unit;

    /**
     * 商品详情 
     */
    private String detailHtml;

    /**
     * 移动端商品详情 
     */
    private String detailMobileHtml;

    /**
     * 赠送积分 
     */
    private Integer giftPoint;

    /**
     * 赠送成长值 
     */
    private Integer giftGrowth;

    /**
     * 限制使用的积分数 
     */
    private Integer usePointLimit;

    /**
     * 是否新品
     */
    private Boolean newStatus;

    /**
     * 是否推荐
     */
    private Boolean recommendStatus;

    /**
     * 服务保障
     */
    private List<Integer> serviceIds;

    /**
     * 副标题 
     */
    private String subTitle;

    /**
     * 关键字 
     */
    private String keyword;

    /**
     * 简介 
     */
    private String intro;

}