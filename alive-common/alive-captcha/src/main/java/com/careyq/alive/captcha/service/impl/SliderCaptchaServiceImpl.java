package com.careyq.alive.captcha.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.careyq.alive.captcha.constants.CaptchaResultCode;
import com.careyq.alive.captcha.constants.CaptchaTypeEnum;
import com.careyq.alive.captcha.constants.Constants;
import com.careyq.alive.captcha.domain.CaptchaCheck;
import com.careyq.alive.captcha.domain.CaptchaInfo;
import com.careyq.alive.captcha.domain.SliderCaptchaInfo;
import com.careyq.alive.captcha.domain.Template;
import com.careyq.alive.captcha.util.ImageUtils;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.util.AesUtils;
import com.careyq.alive.core.util.JsonUtils;
import com.careyq.alive.core.util.ServletUtils;
import com.careyq.alive.redis.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Objects;

/**
 * 滑动验证码
 *
 * @author CareyQ
 */
@Slf4j
public class SliderCaptchaServiceImpl extends AbstractCaptchaService {

    @Override
    public CaptchaInfo generate() {
        Template template = ImageUtils.randomTemplate(captchaType());
        BufferedImage backgroundImage = ImageUtils.base64ToImage(ImageUtils.randomBackgroundImage());
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

        return SliderCaptchaInfo.of(ImageUtils.imageToBase64(backgroundImage),
                ImageUtils.imageToBase64(matrixTemplate),
                backgroundImage.getWidth(),
                backgroundImage.getHeight(),
                matrixTemplate.getWidth(),
                matrixTemplate.getHeight(),
                randomX,
                randomY
        );
    }

    @Override
    public CaptchaInfo get() {
        validGet(ServletUtils.getClientIp() + ServletUtils.getUserAgentInfo());
        CaptchaInfo info = generate();
        CaptchaInfo res = generateToken(info);

        SliderCaptchaInfo.Point point = ((SliderCaptchaInfo) info).getPoint();
        point.setSecretKey(res.getSecretKey());
        RedisUtils.set(Constants.CAPTCHA_TOKEN + res.getToken(), point, 120);
        return res;
    }

    @Override
    public String captchaType() {
        return CaptchaTypeEnum.SLIDER.getType();
    }

    @Override
    protected void doInit() {
        List<String> template1 = ImageUtils.getResourcesFile(Constants.TEMPLATE_PATH.concat("/1/*.png"));
        List<String> template2 = ImageUtils.getResourcesFile(Constants.TEMPLATE_PATH.concat("/2/*.png"));

        ImageUtils.addTemplate(captchaType(), new Template(template1.get(0), template1.get(1)));
        ImageUtils.addTemplate(captchaType(), new Template(template2.get(0), template2.get(1)));
    }

    @Override
    public String check(CaptchaCheck check) {
        String clientUid = getClientUid(ServletUtils.getClientIp() + ServletUtils.getUserAgentInfo());
        validCheck(clientUid);
        String key = Constants.CAPTCHA_TOKEN + check.getToken();
        SliderCaptchaInfo.Point point = RedisUtils.get(key, SliderCaptchaInfo.Point.class);
        if (point == null) {
            throw new CustomException(CaptchaResultCode.CAPTCHA_INVALID);
        }
        RedisUtils.del(key);
        SliderCaptchaInfo.Point newPoint;
        String secretKey;
        try {
            secretKey = point.getSecretKey();
            newPoint = JsonUtils.parseObject(AesUtils.decrypt(check.getPoint(), secretKey), SliderCaptchaInfo.Point.class);
            if (newPoint == null) {
                throw new NullPointerException();
            }
        } catch (Exception e) {
            log.error("验证码坐标解析失败", e);
            failCheck(clientUid);
            throw new CustomException(CaptchaResultCode.CAPTCHA_CHECK_FAIL);
        }

        if (point.getX() - OFFSET > newPoint.getX() || newPoint.getX() > point.getX() + OFFSET || !Objects.equals(point.getY(), newPoint.getY())) {
            failCheck(clientUid);
            throw new CustomException(CaptchaResultCode.CAPTCHA_CHECK_FAIL);
        }

        String authenticate = IdUtil.fastSimpleUUID();
        RedisUtils.set(Constants.CAPTCHA_VERIFY + authenticate, true, 120);
        return authenticate;
    }
}
