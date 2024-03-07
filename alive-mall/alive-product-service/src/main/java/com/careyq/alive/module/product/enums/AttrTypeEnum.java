package com.careyq.alive.module.product.enums;

import com.careyq.alive.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品属性类型
 *
 * @author CareyQ
 */
@Getter
@AllArgsConstructor
public enum AttrTypeEnum implements IEnum {

    /**
     * 规格
     */
    SPEC(0, "规格"),
    /**
     * 参数
     */
    PARAM(1, "参数");

    private final Integer code;
    private final String desc;

}
