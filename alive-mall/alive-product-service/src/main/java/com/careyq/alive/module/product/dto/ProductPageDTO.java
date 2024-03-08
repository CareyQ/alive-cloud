package com.careyq.alive.module.product.dto;

import com.careyq.alive.core.domain.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * 商品信息分页 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品信息分页 DTO")
public class ProductPageDTO extends PageDTO {

    @Schema(description = "所属分类")
    private Long categoryId;

    @Schema(description = "所属品牌")
    private Long brandId;

    @Schema(description = "商品编号")
    private String snCode;

    @Schema(description = "商品名称")
    private String name;

    @Schema(description = "状态，0下架 1上架")
    private Integer status;

}