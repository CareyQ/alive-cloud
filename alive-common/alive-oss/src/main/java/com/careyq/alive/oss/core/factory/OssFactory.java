package com.careyq.alive.oss.core.factory;

import cn.hutool.cache.CacheUtil;
import com.careyq.alive.core.constants.ResultCodeConstants;
import com.careyq.alive.core.domain.ResultCode;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.oss.core.client.OssClient;
import com.careyq.alive.redis.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 对象存储工厂
 *
 * @author CareyQ
 */
@Slf4j
public class OssFactory {

    public static final String DEFAULT_CONFIG_ID = "sys_oss:default_config";

    public static OssClient instance() {
        Long configId = RedisUtils.getObject(DEFAULT_CONFIG_ID);
        if (configId == null) {
            log.error("未找到默认的对象存储配置");
            throw new CustomException(ResultCodeConstants.SERVER_ERROR);
        }

    }

    public static OssClient instance(Long configId) {
        CacheUtil
        return null;
    }
}
