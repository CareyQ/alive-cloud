package com.careyq.alive.captcha.domain;

import com.careyq.alive.captcha.constants.CaptchaTypeEnum;
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


    public static SliderCaptchaInfo of(String backgroundImage,
                                       String templateImage,
                                       Integer backgroundImageWidth,
                                       Integer backgroundImageHeight,
                                       Integer templateImageWidth,
                                       Integer templateImageHeight,
                                       Integer x,
                                       Integer y) {
        SliderCaptchaInfo info = new SliderCaptchaInfo();
        info.setX(x);
        info.setY(y);
        info.setBackgroundImage(backgroundImage);
        info.setTemplateImage(templateImage);
        info.setBackgroundImageWidth(backgroundImageWidth);
        info.setBackgroundImageHeight(backgroundImageHeight);
        info.setTemplateImageWidth(templateImageWidth);
        info.setTemplateImageHeight(templateImageHeight);
        info.setType(CaptchaTypeEnum.SLIDER.getType());
        return info;
    }
}
