package com.careyq.alive.captcha.domain;

import lombok.Data;

/**
 * 验证码信息
 *
 * @author CareyQ
 */
@Data
public class CaptchaInfo {

    /**
     * 背景图
     */
    private String backgroundImage;
    /**
     * 模板图
     */
    private String templateImage;
    /**
     * 背景图片宽度
     */
    private Integer backgroundImageWidth;
    /**
     * 背景图片高度
     */
    private Integer backgroundImageHeight;
    /**
     * 滑块图片宽度
     */
    private Integer templateImageWidth;
    /**
     * 滑块图片高度
     */
    private Integer templateImageHeight;
    /**
     * 随机值
     */
    private Integer randomX;
    /**
     * 容错值, 可以为空 默认 0.02 容错,校验的时候用
     */
    private Float tolerant;
    /**
     * 验证码类型
     */
    private String type;
}
