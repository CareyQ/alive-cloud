package com.careyq.alive.module.product.vo;

import com.careyq.alive.module.product.enums.AttributeTypeEnum;
import com.careyq.alive.module.product.enums.InputTypeEnum;
import com.careyq.alive.module.product.enums.SelectTypeEnum;
import com.careyq.alive.web.jackson.EnumName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 商品属性分页 VO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 商品属性分页 VO")
public class ProductAttributePageVO {

    @Schema(description = "主键")
    private Long id;

    @EnumName(AttributeTypeEnum.class)
    @Schema(description = "属性类型，0规格 1参数")
    private Integer type;

    @Schema(description = "属性名称")
    private String name;

    @EnumName(SelectTypeEnum.class)
    @Schema(description = "属性选择类型，0唯一 1单选 2多选")
    private Integer selectType;

    @EnumName(InputTypeEnum.class)
    @Schema(description = "属性录入方式，0手工录入 1从列表中选取")
    private Integer inputType;

    @Schema(description = "可选值列表，以逗号隔开")
    private String inputList;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "支持新增")
    private Boolean addition;

}