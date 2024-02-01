package com.careyq.alive.module.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 商品品牌 VO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品品牌 VO")
public class ProductBrandVO {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "品牌名称")
    private String name;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "品牌 logo")
    private String logo;

    @Schema(description = "品牌描述")
    private String description;

    @Schema(description = "状态")
    private Integer status;

}