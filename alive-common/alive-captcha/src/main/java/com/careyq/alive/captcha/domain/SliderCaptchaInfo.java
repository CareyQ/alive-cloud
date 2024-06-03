package com.careyq.alive.captcha.domain;

import com.careyq.alive.captcha.constants.CaptchaTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 滑动验证码信息
 *
 * @author CareyQ
 */
@Data
public class SliderCaptchaInfo extends CaptchaInfo {

    /**
     * 点位
     */
    private Point point;


    @Data
    @NoArgsConstructor
    public static class Point {
        /**
         * x轴
         */
        private Integer x;
        /**
         * y轴
         */
        private Integer y;
        /**
         * secretKey
         */
        private String secretKey;

        public Point(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }
    }


    public static SliderCaptchaInfo of(String backgroundImage,
                                       String templateImage,
                                       Integer backgroundImageWidth,
                                       Integer backgroundImageHeight,
                                       Integer templateImageWidth,
                                       Integer templateImageHeight,
                                       Integer x,
                                       Integer y) {
        SliderCaptchaInfo info = new SliderCaptchaInfo();
        info.setBackgroundImage(backgroundImage);
        info.setTemplateImage(templateImage);
        info.setBackgroundImageWidth(backgroundImageWidth);
        info.setBackgroundImageHeight(backgroundImageHeight);
        info.setTemplateImageWidth(templateImageWidth);
        info.setTemplateImageHeight(templateImageHeight);
        info.setType(CaptchaTypeEnum.SLIDER.getType());
        info.setPoint(new Point(x, y));
        return info;
    }
}
