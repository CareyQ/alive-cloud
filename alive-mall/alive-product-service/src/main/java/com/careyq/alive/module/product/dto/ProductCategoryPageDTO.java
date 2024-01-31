package com.careyq.alive.module.product.dto;

import com.careyq.alive.core.domain.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商品分类分页 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品分类分页 DTO")
public class ProductCategoryPageDTO extends PageDTO {

    @Schema(description = "父级分类 ID")
    private Long parentId;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "状态，0停用 1正常")
    private Integer status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}