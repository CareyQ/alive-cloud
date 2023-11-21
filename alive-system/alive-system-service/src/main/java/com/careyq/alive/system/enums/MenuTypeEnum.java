package com.careyq.alive.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型枚举
 *
 * @author CareyQ
 * @since 2023-11-17
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {

    /**
     * 目录
     */
    DIR(1),
    /**
     * 分组
     */
    GROUP(2),
    /**
     * 菜单
     */
    MENU(3),
    /**
     * 按钮
     */
    BUTTON(4),
    ;

    private final Integer type;
}
