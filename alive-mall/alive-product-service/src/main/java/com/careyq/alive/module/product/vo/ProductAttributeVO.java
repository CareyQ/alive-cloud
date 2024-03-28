package com.careyq.alive.module.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 商品属性 VO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品属性 VO")
public class ProductAttributeVO {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "属性名称")
    private String name;

}