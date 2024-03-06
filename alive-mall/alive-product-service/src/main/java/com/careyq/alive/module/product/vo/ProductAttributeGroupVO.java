package com.careyq.alive.module.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 商品属性分组 VO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品属性分组 VO")
public class ProductAttributeGroupVO {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "分类 ID")
    private Long categoryId;

    @Schema(description = "属性分组名称")
    private String name;

    @Schema(description = "排序")
    private Integer sort;

}