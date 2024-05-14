package com.careyq.alive.captcha.service.impl;

import com.careyq.alive.captcha.domain.CaptchaInfo;
import com.careyq.alive.captcha.service.CaptchaService;

import java.util.Properties;

/**
 * 验证码服务
 *
 * @author CareyQ
 */
public abstract class AbstractCaptchaServiceImpl implements CaptchaService {

    @Override
    public void init(Properties config) {

    }

    @Override
    public CaptchaInfo get(String clientId) {
        return null;
    }
}
