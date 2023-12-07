package com.careyq.alive.operatelog.core.annotations;

import com.careyq.alive.operatelog.core.enums.OperateTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作日志注解
 *
 * @author CareyQ
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperateLog {

    /**
     * 操作模块
     * 为空时，会尝试读取 {@link io.swagger.v3.oas.annotations.tags.Tag#name()} 属性
     */
    String module() default "";

    /**
     * 操作名
     * 为空时，会尝试读取 {@link io.swagger.v3.oas.annotations.Operation#summary()} 属性
     */
    String name() default "";

    /**
     * 是否记录操作日志
     *
     * @return 结果
     */
    boolean enable() default true;

    /**
     * 操作分类
     * 实际并不是数组，因为枚举不能设置 null 作为默认值
     */
    OperateTypeEnum[] type() default {};

    /**
     * 是否记录方法参数
     */
    boolean logArgs() default true;

    /**
     * 是否记录方法结果的数据
     */
    boolean logResultData() default true;
}
