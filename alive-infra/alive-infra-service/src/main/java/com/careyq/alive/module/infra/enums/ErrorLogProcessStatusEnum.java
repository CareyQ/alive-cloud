package com.careyq.alive.module.infra.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误日志处理状态枚举
 *
 * @author CareyQ
 */
@Getter
@AllArgsConstructor
public enum ErrorLogProcessStatusEnum {

    /**
     * 日志状态
     */
    INIT(0, "未处理"),
    DONE(1, "已处理"),
    IGNORE(2, "已忽略");

    /**
     * 状态
     */
    private final Integer status;
    /**
     * 资源类型名
     */
    private final String name;
}
