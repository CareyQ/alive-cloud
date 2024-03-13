package com.careyq.alive.module.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 商品属性列表 VO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品属性列表 VO")
public class ProductAttributeListVO {

    @Schema(description = "所属分组")
    private Long groupId;

    @Schema(description = "所属分组")
    private String groupName;

    @Schema(description = "参数")
    private List<ProductAttributeVO> attributes;

}