package com.careyq.alive.module.product.enums;

import com.careyq.alive.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品属性录入方式
 *
 * @author CareyQ
 */
@Getter
@AllArgsConstructor
public enum InputTypeEnum implements IEnum {

    /**
     * 手工录入
     */
    MANUAL(0, "手工录入"),
    /**
     * 从列表中选取
     */
    LIST(1, "从列表中选取");

    private final Integer code;
    private final String desc;

}
