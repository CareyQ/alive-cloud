package com.careyq.alive.captcha.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 验证码类型枚举
 *
 * @author CareyQ
 */
@Getter
@AllArgsConstructor
public enum CaptchaTypeEnum {

    /**
     * 滑动验证码
     */
    SLIDER("slider", "滑动验证码");

    private final String type;

    private final String desc;
}
