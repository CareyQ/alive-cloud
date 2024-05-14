package com.careyq.alive.captcha.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.FileCopyUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * 图片工具类
 *
 * @author CareyQ
 */
@Slf4j
public class ImageUtils {

    /**
     * 获取资源
     *
     * @param path 路径
     * @return 资源
     */
    public static List<String> getResourcesFile(String path) {
        List<String> files = new ArrayList<>();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            Resource[] resources = resolver.getResources(path);
            for (Resource resource : resources) {
                byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
                files.add(Base64.getEncoder().encodeToString(bytes));
            }
        } catch (IOException e) {
            log.error("[ImageUtils getResourcesFile] error", e);
        }
        return files;
    }

    /**
     * base64转图片
     *
     * @param value base64
     * @return 图片
     */
    public static BufferedImage base64ToImage(String value) {
        try {
            byte[] bytes = Base64.getDecoder().decode(value);
            return ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            log.error("[ImageUtils base64ToImage] error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 图片转base64
     *
     * @param image 图片
     * @return base64
     */
    public static String imageToBase64(BufferedImage image) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, "png", os);
            return Base64.getEncoder().encodeToString(os.toByteArray());
        } catch (IOException e) {
            log.error("[ImageUtils base64ToImage] error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过模板图片抠图（不透明部分）
     *
     * @param oriImage      源图片
     * @param templateImage 模板图片
     * @param xPos          坐标轴x
     * @param yPos          坐标轴y
     * @return BufferedImage
     */
    public static BufferedImage cutImage(BufferedImage oriImage, BufferedImage templateImage, int xPos, int yPos) {
        // 模板图像矩阵
        int bw = templateImage.getWidth(null);
        int bh = templateImage.getHeight(null);
        BufferedImage targetImage = new BufferedImage(bw, bh, BufferedImage.TYPE_INT_ARGB);
        // 透明色
        for (int y = 0; y < bh; y++) {
            for (int x = 0; x < bw; x++) {
                int rgb = templateImage.getRGB(x, y);
                int alpha = (rgb >> 24) & 0xff;
                // 透明度大于100才处理，过滤一下边缘过于透明的像素点
                if (alpha > 100) {
                    int bgRgb = oriImage.getRGB(xPos + x, yPos + y);
                    targetImage.setRGB(x, y, bgRgb);
                }
            }

        }
        return targetImage;
    }

    /**
     * 图片覆盖（覆盖图压缩到width*height大小，覆盖到底图上）
     *
     * @param baseBufferedImage  底图
     * @param coverBufferedImage 覆盖图
     * @param x                  起始x轴
     * @param y                  起始y轴
     */
    public static void overlayImage(BufferedImage baseBufferedImage, BufferedImage coverBufferedImage, int x, int y) {
        // 创建Graphics2D对象，用在底图对象上绘图
        Graphics2D g2d = baseBufferedImage.createGraphics();
        // 绘制
        g2d.drawImage(coverBufferedImage, x, y, coverBufferedImage.getWidth(), coverBufferedImage.getHeight(), null);
        // 释放图形上下文使用的系统资源
        g2d.dispose();
    }

    /**
     * 创建透明图片
     *
     * @param width  宽度
     * @param height 高度
     * @return 图片
     */
    public static BufferedImage createTransparentImage(int width, int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }
}
