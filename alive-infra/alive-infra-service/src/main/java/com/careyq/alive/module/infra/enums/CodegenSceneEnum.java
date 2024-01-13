package com.careyq.alive.module.infra.enums;

import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 代码生成的场景枚举
 *
 * @author CareyQ
 */
@Getter
@AllArgsConstructor
public enum CodegenSceneEnum {

    /**
     * 管理后台
     */
    ADMIN(1, "管理后台", "admin", ""),
    /**
     * 用户 APP
     */
    APP(2, "用户 APP", "app", "App"),
    ;

    /**
     * 场景
     */
    private final Integer scene;
    /**
     * 资源类型名
     */
    private final String name;
    /**
     * 基础包名
     */
    private final String basePackage;
    /**
     * 类前缀
     */
    private final String prefixClass;

    public static CodegenSceneEnum valueOf(Integer scene) {
        return ArrayUtil.firstMatch(sceneEnum -> sceneEnum.getScene().equals(scene), values());
    }
}
