package com.careyq.alive.captcha.constants;

import com.careyq.alive.core.domain.ResultCode;

/**
 * 错误码常量
 *
 * @author CareyQ
 */
public interface CaptchaResultCode {

    ResultCode NONSUPPORT_CAPTCHA_TYPE = new ResultCode(5100, "不支持的验证码类型 {}");
    ResultCode BACKGROUND_IMAGE_EMPTY = new ResultCode(5101, "验证码背景图资源为空");
    ResultCode TEMPLATE_EMPTY = new ResultCode(5102, "验证码模版资源为空");
    ResultCode CAPTCHA_LOCK = new ResultCode(5103, "验证失败数过多，请稍后再试");
    ResultCode CAPTCHA_LIMIT = new ResultCode(5104, "验证码获取次数过多，请稍后再试");
    ResultCode CAPTCHA_INVALID = new ResultCode(5105, "验证码已失效，请重新获取");
    ResultCode CAPTCHA_CHECK_LIMIT = new ResultCode(5106, "验证码校验次数过多，请稍后再试");
    ResultCode CAPTCHA_CHECK_FAIL = new ResultCode(5107, "验证失败");
}
