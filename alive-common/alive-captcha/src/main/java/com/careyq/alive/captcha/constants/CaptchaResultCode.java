package com.careyq.alive.captcha.constants;

import com.careyq.alive.core.domain.ResultCode;

/**
 * 错误码常量
 *
 * @author CareyQ
 */
public interface CaptchaResultCode {

    ResultCode NONSUPPORT_CAPTCHA_TYPE = new ResultCode(500, "不支持的验证码类型 {}");
    ResultCode BACKGROUND_IMAGE_EMPTY = new ResultCode(500, "验证码背景图资源为空");
    ResultCode TEMPLATE_EMPTY = new ResultCode(500, "验证码模版资源为空");
}
