package com.careyq.alive.module.product.enums;

import com.careyq.alive.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品属性选择类型
 *
 * @author CareyQ
 */
@Getter
@AllArgsConstructor
public enum SelectTypeEnum implements IEnum {

    /**
     * 唯一
     */
    UNIQUE(0, "唯一"),
    /**
     * 单选
     */
    SINGLE(1, "单选"),
    /**
     * 多选
     */
    MULTIPLE(2, "多选");

    private final Integer code;
    private final String desc;

}
