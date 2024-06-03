package com.careyq.alive.core.util;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

import java.nio.charset.StandardCharsets;

/**
 * AES 工具类
 *
 * @author CareyQ
 */
public class AesUtils {

    /**
     * 获取 AES 密钥
     *
     * @return AES 密钥
     */
    public static String key() {
        return RandomUtil.randomString(RandomUtil.BASE_CHAR, 16);
    }

    /**
     * 加密
     *
     * @param content 待加密内容
     * @param key     密钥
     * @return 加密后的内容
     */
    public static String encrypt(String content, String key) {
        AES aes = SecureUtil.aes(key.getBytes(StandardCharsets.UTF_8));
        return aes.encryptBase64(content);
    }

    /**
     * 解密
     *
     * @param content 待解密内容
     * @param key     密钥
     * @return 解密后内容
     */
    public static String decrypt(String content, String key) {
        AES aes = SecureUtil.aes(key.getBytes(StandardCharsets.UTF_8));
        return aes.decryptStr(content);
    }

}
