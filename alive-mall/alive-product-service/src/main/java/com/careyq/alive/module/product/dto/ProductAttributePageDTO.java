package com.careyq.alive.module.product.dto;

import com.careyq.alive.core.domain.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 商品属性分页 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品属性分页 DTO")
public class ProductAttributePageDTO extends PageDTO {

    @NotNull(message = "所属分组不能为空")
    @Schema(description = "所属分组")
    private Long groupId;

    @NotNull(message = "属性类型不能为空")
    @Schema(description = "属性类型，0规格 1参数")
    private Integer type;

    @Schema(description = "属性名称")
    private String name;

}