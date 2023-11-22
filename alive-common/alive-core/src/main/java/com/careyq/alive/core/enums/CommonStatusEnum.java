package com.careyq.alive.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用状态枚举
 *
 * @author CareyQ
 */
@Getter
@AllArgsConstructor
public enum CommonStatusEnum {

    ENABLE(1, "启用"),
    DISABLE(0, "禁用");

    private final Integer status;

    private final String name;
}
