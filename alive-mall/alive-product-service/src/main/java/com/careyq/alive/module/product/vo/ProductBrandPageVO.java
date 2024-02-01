package com.careyq.alive.module.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 商品品牌分页 VO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品品牌分页 VO")
public class ProductBrandPageVO {

    @Schema(description = "编号")
    private Long id;

    @Schema(description = "品牌名称")
    private String name;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "品牌 logo")
    private String logo;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}