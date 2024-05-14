package com.careyq.alive.captcha.service.impl;

import com.careyq.alive.captcha.domain.CaptchaInfo;
import com.careyq.alive.captcha.service.CaptchaService;

import java.util.Properties;

/**
 * 滑动验证码
 *
 * @author CareyQ
 */
public class SliderCaptchaServiceImpl implements CaptchaService {

    @Override
    public void init(Properties config) {

    }

    @Override
    public CaptchaInfo get(String clientId) {
        return null;
    }
}
