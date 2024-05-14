package com.careyq.alive.captcha.service.impl;

import cn.hutool.core.map.MapUtil;
import com.careyq.alive.captcha.service.CaptchaService;
import com.careyq.alive.core.exception.CustomException;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.ServiceLoader;

import static com.careyq.alive.captcha.constants.CaptchaResultCode.NONSUPPORT_CAPTCHA_TYPE;

/**
 * 验证码服务工厂
 *
 * @author CareyQ
 */
@Slf4j
public class CaptchaServiceFactory {

    public volatile static Map<String, CaptchaService> instances = MapUtil.newHashMap();

    static {
        ServiceLoader<CaptchaService> services = ServiceLoader.load(CaptchaService.class);
        services.forEach(service -> instances.put(service.captchaType(), service));
        log.info("[alive][load][加载验证码服务成功]");
    }

    /**
     * 获取验证码服务
     *
     * @param type 验证码类型
     * @return 验证码服务
     */
    public static CaptchaService getInstance(String type) {
        CaptchaService service = instances.get(type);
        if (service == null) {
            throw new CustomException(NONSUPPORT_CAPTCHA_TYPE, type);
        }
        service.init();
        return service;
    }
}
