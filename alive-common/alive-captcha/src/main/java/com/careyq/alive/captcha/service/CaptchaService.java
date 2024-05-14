package com.careyq.alive.captcha.service;

import com.careyq.alive.captcha.domain.CaptchaInfo;

/**
 * 验证码服务
 *
 * @author CareyQ
 */
public interface CaptchaService {

    /**
     * 初始化配置
     */
    void init();

    /**
     * 获取验证码
     *
     * @param clientId 客户端标识编号
     * @return 验证码信息
     */
    CaptchaInfo get(String clientId);

    /**
     * 获取验证码类型
     *
     * @return 验证码类型
     */
    String captchaType();
}
