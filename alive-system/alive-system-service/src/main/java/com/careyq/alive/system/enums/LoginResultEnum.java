package com.careyq.alive.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录结果枚举
 *
 * @author CareyQ
 */
@Getter
@AllArgsConstructor
public enum LoginResultEnum {

    /**
     * 登录成功
     */
    SUCCESS(0),
    /**
     * 账号或密码不正确
     */
    BAD_CREDENTIALS(10),
    /**
     * 用户被禁用
     */
    USER_DISABLED(20),
    ;

    private final Integer type;
}
