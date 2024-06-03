package com.careyq.alive.captcha.constants;

/**
 * 验证码相关常量
 *
 * @author CareyQ
 */
public interface Constants {

    /**
     * 默认的resource资源文件路径.
     */
    String BACKGROUND_IMAGE_PATH = "META-INF/resource";
    /**
     * 默认的template资源文件路径.
     */
    String TEMPLATE_PATH = "META-INF/template";
    /**
     * 验证码token
     */
    String CAPTCHA_TOKEN = "captcha:token:";
    /**
     * 验证码限流 KEY
     */
    String CAPTCHA_LIMIT = "captcha:token:limit:{}:{}";
    /**
     * 验证码二次校验
     */
    String CAPTCHA_VERIFY = "captcha:verify:";
}
