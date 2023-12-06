package com.careyq.alive.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 全局用户类型枚举
 *
 * @author CareyQ
 */
@Getter
@AllArgsConstructor
public enum UserTypeEnum {

    /**
     * 暂时只存在后台管理用户
     */
    ADMIN(1, "管理员"),
    ;

    private final Integer type;

    private final String name;
}
