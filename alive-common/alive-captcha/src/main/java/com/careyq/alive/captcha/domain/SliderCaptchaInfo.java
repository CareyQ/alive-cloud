package com.careyq.alive.captcha.domain;

import lombok.Data;

/**
 * 滑动验证码信息
 *
 * @author CareyQ
 */
@Data
public class SliderCaptchaInfo extends CaptchaInfo {

    /**
     * x轴
     */
    private Integer x;
    /**
     * y轴
     */
    private Integer y;

}
