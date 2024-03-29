package com.careyq.alive.module.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 商品属性分页 VO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品属性分页 VO")
public class ProductAttributePageVO {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "属性名称")
    private String name;

    @Schema(description = "商品编号")
    private Long productId;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "属性值")
    private Set<String> value;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}