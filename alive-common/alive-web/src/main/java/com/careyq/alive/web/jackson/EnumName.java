package com.careyq.alive.web.jackson;

import com.careyq.alive.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 枚举名称注解，用于序列化枚举时，使用枚举的 name 字段
 *
 * @author CareyQ
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize(using = EnumNameSerializer.class)
public @interface EnumName {

    /**
     * 枚举类型
     *
     * @return 枚举类型
     */
    Class<? extends IEnum> value();

    /**
     * 别名
     *
     * @return 别名
     */
    String alias() default "";
}
