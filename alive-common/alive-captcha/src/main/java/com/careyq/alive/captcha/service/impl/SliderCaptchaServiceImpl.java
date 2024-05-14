package com.careyq.alive.captcha.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.careyq.alive.captcha.constants.CaptchaTypeEnum;
import com.careyq.alive.captcha.constants.Constants;
import com.careyq.alive.captcha.domain.CaptchaInfo;
import com.careyq.alive.captcha.domain.SliderCaptchaInfo;
import com.careyq.alive.captcha.domain.Template;
import com.careyq.alive.captcha.util.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * 滑动验证码
 *
 * @author CareyQ
 */
public class SliderCaptchaServiceImpl extends AbstractCaptchaServiceImpl {

    protected SliderCaptchaServiceImpl(ResourceStore resourceStore) {
        super(resourceStore);
    }

    @Override
    public CaptchaInfo get(String clientId) {
        Template template = resourceStore.randomTemplate(captchaType());
        BufferedImage backgroundImage = ImageUtils.base64ToImage(resourceStore.randomBackgroundImage());
        BufferedImage active = ImageUtils.base64ToImage(template.getActive());
        BufferedImage fixed = ImageUtils.base64ToImage(template.getFixed());

        // 获取随机的 x 和 y 轴
        int randomX = RandomUtil.randomInt(fixed.getWidth() + 5, backgroundImage.getWidth() - fixed.getWidth() - 10);
        int randomY = RandomUtil.randomInt(backgroundImage.getHeight() - fixed.getHeight());

        BufferedImage cutImage = ImageUtils.cutImage(backgroundImage, fixed, randomX, randomY);
        ImageUtils.overlayImage(backgroundImage, fixed, randomX, randomY);

        ImageUtils.overlayImage(cutImage, active, 0, 0);
        // 这里创建一张png透明图片
        BufferedImage matrixTemplate = ImageUtils.createTransparentImage(active.getWidth(), backgroundImage.getHeight());
        ImageUtils.overlayImage(matrixTemplate, cutImage, 0, randomY);

        SliderCaptchaInfo sliderCaptchaInfo = SliderCaptchaInfo.of(ImageUtils.imageToBase64(backgroundImage),
                ImageUtils.imageToBase64(matrixTemplate),
                backgroundImage.getWidth(),
                backgroundImage.getHeight(),
                matrixTemplate.getWidth(),
                matrixTemplate.getHeight(),
                randomX,
                randomY
        );

        return null;
    }

    @Override
    public String captchaType() {
        return CaptchaTypeEnum.SLIDER.getType();
    }

    @Override
    protected void doInit() {
        List<String> template1 = ImageUtils.getResourcesFile(Constants.TEMPLATE_PATH.concat("/1"));
        List<String> template2 = ImageUtils.getResourcesFile(Constants.TEMPLATE_PATH.concat("/2"));

        resourceStore.addTemplate(captchaType(), new Template(template1.get(0), template1.get(1)));
        resourceStore.addTemplate(captchaType(), new Template(template2.get(0), template2.get(1)));
    }
}
