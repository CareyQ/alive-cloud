package com.careyq.alive.module.product.dto;

import com.careyq.alive.core.domain.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * 商品品牌分页 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品品牌分页 DTO")
public class ProductBrandPageDTO extends PageDTO {

    @Schema(description = "品牌名称")
    private String name;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "开始日期")
    private LocalDate startDate;

    @Schema(description = "结束日期")
    private LocalDate endDate;

}