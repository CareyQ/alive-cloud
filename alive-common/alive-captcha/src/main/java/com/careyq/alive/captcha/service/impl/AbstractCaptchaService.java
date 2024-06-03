package com.careyq.alive.captcha.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.careyq.alive.captcha.constants.Constants;
import com.careyq.alive.captcha.domain.CaptchaInfo;
import com.careyq.alive.captcha.service.CaptchaService;
import com.careyq.alive.captcha.util.ImageUtils;
import com.careyq.alive.core.domain.ResultCode;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.util.AesUtils;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.redis.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.careyq.alive.captcha.constants.CaptchaResultCode.*;

/**
 * 验证码服务
 *
 * @author CareyQ
 */
@Slf4j
public abstract class AbstractCaptchaService implements CaptchaService {

    /**
     * 默认偏移量
     */
    protected static final int OFFSET = 5;
    /**
     * 默认限流
     */
    protected static final int LIMIT = 10;
    /**
     * 失败限流
     */
    protected static final int FAIL_LIMIT = 5;

    @Override
    public void init() {
        List<String> backgroundImages = ImageUtils.getResourcesFile(Constants.BACKGROUND_IMAGE_PATH.concat("/*.png"));
        if (CollUtils.isEmpty(backgroundImages)) {
            log.error("[captcha] 验证码背景图片为空");
        } else {
            backgroundImages.forEach(ImageUtils::addBackgroundImage);
        }
        doInit();
    }

    /**
     * 初始化
     */
    protected abstract void doInit();

    /**
     * 生成 Token
     *
     * @param info 验证码信息
     * @return CaptchaInfo
     */
    CaptchaInfo generateToken(CaptchaInfo info) {
        CaptchaInfo captchaInfo = new CaptchaInfo();
        captchaInfo.setBackgroundImage(info.getBackgroundImage())
                .setTemplateImage(info.getTemplateImage())
                .setBackgroundImageWidth(info.getBackgroundImageWidth())
                .setBackgroundImageHeight(info.getBackgroundImageHeight())
                .setTemplateImageWidth(info.getTemplateImageWidth())
                .setTemplateImageHeight(info.getTemplateImageHeight())
                .setType(info.getType())
                .setToken(IdUtil.fastSimpleUUID())
                .setSecretKey(AesUtils.key());
        return captchaInfo;
    }

    /**
     * 获取验证码限流
     *
     * @param browserInfo 浏览器信息
     */
    protected void validGet(String browserInfo) {
        String clientUid = getClientUid(browserInfo);
        if (StrUtil.isEmpty(clientUid)) {
            return;
        }
        String lockKey = StrUtil.format(Constants.CAPTCHA_LIMIT, "LOCK", clientUid);
        if (RedisUtils.has(lockKey)) {
            throw new CustomException(CAPTCHA_LOCK);
        }
        String getKey = StrUtil.format(Constants.CAPTCHA_LIMIT, "GET", clientUid);
        limit(getKey, CAPTCHA_LIMIT);
    }

    /**
     * 验证码校验限流
     *
     * @param clientUid clientUid
     */
    protected void validCheck(String clientUid) {
        if (StrUtil.isEmpty(clientUid)) {
            return;
        }
        String key = StrUtil.format(Constants.CAPTCHA_LIMIT, "CHECK", clientUid);
        limit(key, CAPTCHA_CHECK_LIMIT);
    }

    /**
     * 验证码校验失败限流
     *
     * @param clientUid clientUid
     */
    protected void failCheck(String clientUid) {
        if (StrUtil.isEmpty(clientUid)) {
            return;
        }
        String key = StrUtil.format(Constants.CAPTCHA_LIMIT, "FAIL", clientUid);
        Integer count = RedisUtils.get(key, Integer.class);
        if (count == null) {
            RedisUtils.set(key, 1, 60);
            count = 1;
        }
        RedisUtils.increment(key);
        if (count > FAIL_LIMIT) {
            String lockKey = StrUtil.format(Constants.CAPTCHA_LIMIT, "LOCK", clientUid);
            RedisUtils.set(lockKey, "1", 300);
        }
    }

    /**
     * 限流
     *
     * @param key        key
     * @param resultCode 验证码校验失败限流
     */
    private void limit(String key, ResultCode resultCode) {
        Integer count = RedisUtils.get(key, Integer.class);
        if (count == null) {
            RedisUtils.set(key, 1, 60);
            count = 1;
        }
        RedisUtils.increment(key);
        if (count > LIMIT) {
            throw new CustomException(resultCode);
        }
    }

    /**
     * 获取客户端 Uid
     *
     * @param browserInfo 浏览器信息
     * @return 客户端 Uid
     */
    protected String getClientUid(String browserInfo) {
        if (StrUtil.isEmpty(browserInfo)) {
            return StrUtil.EMPTY;
        }
        return SecureUtil.md5(browserInfo);
    }

    @Override
    public boolean verify(String authenticate) {
        if (StrUtil.isEmpty(authenticate)) {
            return false;
        }
        String key = Constants.CAPTCHA_VERIFY + authenticate;
        if (RedisUtils.has(key)) {
            RedisUtils.del(key);
            return true;
        }
        return false;
    }
}
