package com.careyq.alive.captcha.service.impl;

import com.careyq.alive.captcha.constants.Constants;
import com.careyq.alive.captcha.domain.CaptchaInfo;
import com.careyq.alive.captcha.service.CaptchaService;
import com.careyq.alive.captcha.util.ImageUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 验证码服务
 *
 * @author CareyQ
 */
@Slf4j
public abstract class AbstractCaptchaServiceImpl implements CaptchaService {

    protected final ResourceStore resourceStore;

    protected AbstractCaptchaServiceImpl(ResourceStore resourceStore) {
        this.resourceStore = resourceStore;
    }

    @Override
    public void init() {
        List<String> resourcesFile = ImageUtils.getResourcesFile(Constants.BACKGROUND_IMAGE_RESOURCE_PATH);
        if (resourcesFile.isEmpty()) {
            log.error("[captcha init] background image not found");
        }
        resourcesFile.forEach(resourceStore::addBackgroundImage);
        this.doInit();
    }

    @Override
    public CaptchaInfo get(String clientId) {
        return null;
    }

    /**
     * 初始化
     */
    protected abstract void doInit();

}
