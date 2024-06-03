package com.careyq.alive.captcha.domain;

import lombok.Data;

/**
 * 验证码信息校验
 *
 * @author CareyQ
 */
@Data
public class CaptchaCheck {

    /**
     * token
     */
    private String token;
    /**
     * 点位
     */
    private String point;

}
