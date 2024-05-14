package com.careyq.alive.captcha.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import com.careyq.alive.captcha.domain.Template;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.util.CollUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.careyq.alive.captcha.constants.CaptchaResultCode.BACKGROUND_IMAGE_EMPTY;
import static com.careyq.alive.captcha.constants.CaptchaResultCode.TEMPLATE_EMPTY;

/**
 * 资源存储
 *
 * @author CareyQ
 */
public class ResourceStore {

    /**
     * 背景图片
     */
    private final List<String> backgroundImages = new ArrayList<>();
    /**
     * 模板资源，key：模板类型，value：模板资源
     */
    private final Map<String, List<Template>> templateMap = MapUtil.newHashMap();

    public void addBackgroundImage(String image) {
        backgroundImages.add(image);
    }

    public void addTemplate(String type, Template template) {
        templateMap.computeIfAbsent(type, k -> new ArrayList<>()).add(template);
    }

    public String randomBackgroundImage() {
        if (backgroundImages.isEmpty()) {
            throw new CustomException(BACKGROUND_IMAGE_EMPTY);
        }
        if (backgroundImages.size() == 1) {
            return backgroundImages.getFirst();
        }
        return backgroundImages.get(RandomUtil.randomInt(backgroundImages.size()));
    }

    public Template randomTemplate(String type) {
        if (templateMap.isEmpty()) {
            throw new CustomException(TEMPLATE_EMPTY);
        }
        List<Template> templates = templateMap.get(type);
        if (CollUtils.isEmpty(templates)) {
            throw new CustomException(TEMPLATE_EMPTY);
        }
        if (templates.size() == 1) {
            return templates.getFirst();
        }
        return templates.get(RandomUtil.randomInt(templates.size()));
    }
}
