package com.careyq.alive.module.product.enums;

import com.careyq.alive.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品状态枚举
 *
 * @author CareyQ
 */
@Getter
@AllArgsConstructor
public enum ProductStatusEnum implements IEnum {

    /**
     * 下架
     */
    DOWN(0, "下架"),
    /**
     * 上架
     */
    UP(1, "上架");

    private final Integer code;
    private final String desc;

}
