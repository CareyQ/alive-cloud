package com.careyq.alive.captcha.service;

import com.careyq.alive.captcha.domain.CaptchaInfo;

import java.util.Properties;

/**
 * 验证码服务
 *
 * @author CareyQ
 */
public interface CaptchaService {

    /**
     * 初始化配置
     *
     * @param config 属性配置
     */
    void init(Properties config);

    /**
     * 获取验证码
     *
     * @param clientId 客户端标识编号
     * @return 验证码信息
     */
    CaptchaInfo get(String clientId);
}
