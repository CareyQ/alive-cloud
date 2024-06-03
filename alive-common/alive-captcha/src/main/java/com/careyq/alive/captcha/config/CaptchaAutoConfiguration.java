package com.careyq.alive.captcha.config;

import com.careyq.alive.captcha.constants.CaptchaTypeEnum;
import com.careyq.alive.captcha.service.CaptchaService;
import com.careyq.alive.captcha.service.impl.CaptchaServiceFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 验证码自动配置
 *
 * @author CareyQ
 */
@AutoConfiguration
public class CaptchaAutoConfiguration {

    @Bean
    public CaptchaService captchaService() {
        // 暂时只有一种验证码，直接写死
        return CaptchaServiceFactory.getInstance(CaptchaTypeEnum.SLIDER.getType());
    }

}
