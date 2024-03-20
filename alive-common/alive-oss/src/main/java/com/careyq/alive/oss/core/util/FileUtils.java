package com.careyq.alive.oss.core.util;

import com.careyq.alive.oss.core.OssProperties;
import com.careyq.alive.oss.core.factory.OssFactory;

/**
 * 文件工具类
 *
 * @author CareyQ
 */
public class FileUtils {

    /**
     * 获取文件 URL
     *
     * @param path 文件路径
     * @return 文件 URL
     */
    public static String getFileUrl(String path) {
        return getFileDomain() + path;
    }

    /**
     * 获取文件域名
     *
     * @return 文件域名
     */
    public static String getFileDomain() {
        OssProperties properties = OssFactory.getOssProperties();
        return "https://" + properties.getBucket() + "." + properties.getEndpoint() + "/";
    }
}
