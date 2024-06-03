package com.careyq.alive.captcha.service;

import com.careyq.alive.captcha.domain.CaptchaCheck;
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
     * 生成验证码
     *
     * @return 验证码信息
     */
    CaptchaInfo generate();

    /**
     * 获取验证码
     *
     * @return 验证码
     */
    CaptchaInfo get();

    /**
     * 获取验证码类型
     *
     * @return 验证码类型
     */
    String captchaType();

    /**
     * 校验验证码
     *
     * @param check 校验信息
     * @return 结果
     */
    String check(CaptchaCheck check);

    /**
     * 二次验证后端
     *
     * @param authenticate 验证码
     * @return 结果
     */
    boolean verify(String authenticate);
}
