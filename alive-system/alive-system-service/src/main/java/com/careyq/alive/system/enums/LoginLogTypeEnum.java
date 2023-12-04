package com.careyq.alive.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录日志类型枚举
 *
 * @author CareyQ
 */
@Getter
@AllArgsConstructor
public enum LoginLogTypeEnum {

    /**
     * 账号登录
     */
    LOGIN_USERNAME(100),

    /**
     * 自己退出
     */
    LOGOUT_SELF(200),
    ;

    private final Integer type;
}
