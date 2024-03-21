package com.careyq.alive.search.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品 SKU 信息
 *
 * @author CareyQ
 */
@Data
public class EsProductDTO {

    /**
     * 商品编号
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 图片
     */
    private String pic;

    /**
     * 销量
     */
    private Integer salesVolume;

    /**
     * 是否有库存
     */
    private Boolean hasStock;

    /**
     * 热度
     */
    private Long hotScore;

    /**
     * 品牌编号
     */
    private Long brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌 logo
     */
    private String brandLogo;

    /**
     * 分类编号
     */
    private Long categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 规格
     */
    private List<Attrs> attrs;

    @Data
    @AllArgsConstructor
    public static class Attrs {
        /**
         * 属性编号
         */
        private Long attrId;

        /**
         * 属性名称
         */
        private String attrName;

        /**
         * 属性值编号
         */
        private Long valueId;

        /**
         * 属性值
         */
        private String value;

        /**
         * 类型
         */
        private Integer type;
    }
}
