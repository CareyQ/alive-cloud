package com.careyq.alive.module.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.careyq.alive.core.domain.BaseEntity;
import com.careyq.alive.core.util.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品 SKU 信息
 *
 * @author CareyQ
 */
@Data
@TableName(value = "product_sku", autoResultMap = true)
public class ProductSku extends BaseEntity {

    /**
     * 商品 ID
     */
    private Long productId;

    /**
     * 规格
     */
    @TableField(typeHandler = SpecHandler.class)
    private List<Spec> spec;

    /**
     * SKU 编码
     */
    private String skuCode;

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
     * SKU 图片数组
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> albumPics;

    /**
     * 商品重量，千克
     */
    private BigDecimal weight;

    /**
     * 商品体积，立方米
     */
    private BigDecimal volume;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Spec {

        /**
         * 属性编号
         * 关联 {@link ProductAttribute#getId()}
         */
        private Long attributeId;
        /**
         * 属性名字
         * 冗余 {@link ProductAttribute#getName()}
         */
        private String attributeName;

        /**
         * 属性值编号
         * 关联 {@link ProductAttributeValue#getId()}
         */
        private Long valueId;
        /**
         * 属性值名字
         * 冗余 {@link ProductAttributeValue#getValue()} ()}
         */
        private String value;

    }

    public static class SpecHandler extends AbstractJsonTypeHandler<Object> {

        @Override
        protected Object parse(String json) {
            return JsonUtils.parseArray(json, Spec.class);
        }

        @Override
        protected String toJson(Object obj) {
            return JsonUtils.toJson(obj);
        }
    }
}