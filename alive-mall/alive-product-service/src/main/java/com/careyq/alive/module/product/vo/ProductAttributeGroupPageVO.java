package com.careyq.alive.module.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 商品属性分组分页 VO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品属性分组分页 VO")
public class ProductAttributeGroupPageVO {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "分类 ID")
    private Long categoryId;

    @Schema(description = "分类名称")
    private String categoryName;

    @Schema(description = "属性分组名称")
    private String name;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}