package com.careyq.alive.oss.core;

import lombok.Data;

/**
 * 对象存储配置属性
 *
 * @author CareyQ
 */
@Data
public class OssProperties {

    /**
     * 访问站点
     */
    private String endpoint;

    /**
     * ACCESS_KEY
     */
    private String accessKey;

    /**
     * SECRET_KEY
     */
    private String secretKey;

    /**
     * 存储空间名
     */
    private String bucket;

    /**
     * 存储区域
     */
    private String region;

}
