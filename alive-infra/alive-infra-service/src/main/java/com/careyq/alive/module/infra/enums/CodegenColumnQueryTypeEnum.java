package com.careyq.alive.module.infra.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 代码生成器查询字段过滤条件枚举
 *
 * @author CareyQ
 */
@Getter
@AllArgsConstructor
public enum CodegenColumnQueryTypeEnum {

    /**
     * 条件类型
     */
    EQ("="),
    NE("!="),
    GT(">"),
    GTE(">="),
    LT("<"),
    LTE("<="),
    LIKE("LIKE"),
    BETWEEN("BETWEEN");

    private final String type;
}
