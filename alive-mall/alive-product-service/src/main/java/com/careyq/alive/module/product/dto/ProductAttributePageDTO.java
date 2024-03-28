package com.careyq.alive.module.product.dto;

import com.careyq.alive.core.domain.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 商品属性分页 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品属性分页 DTO")
public class ProductAttributePageDTO extends PageDTO {

    @Schema(description = "属性名称")
    private String name;

}