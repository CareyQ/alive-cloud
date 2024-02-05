package com.careyq.alive.oss.core.factory;

import com.careyq.alive.core.constants.CacheNames;
import com.careyq.alive.core.constants.ResultCodeConstants;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.util.JsonUtils;
import com.careyq.alive.oss.core.OssProperties;
import com.careyq.alive.oss.core.client.OssClient;
import com.careyq.alive.redis.util.CacheUtils;
import com.careyq.alive.redis.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.careyq.alive.oss.constants.OssConstants.DEFAULT_CONFIG_ID;

/**
 * 对象存储工厂
 *
 * @author CareyQ
 */
@Slf4j
public class OssFactory {

    private static final Map<Long, OssClient> CLIENT_CACHE = new ConcurrentHashMap<>();

    /**
     * 获取对象存储客户端
     *
     * @return 对象存储客户端
     */
    public static OssClient instance() {
        Object object = RedisUtils.getObject(DEFAULT_CONFIG_ID);
        if (object == null) {
            log.error("未找到默认的对象存储配置");
            throw new CustomException(ResultCodeConstants.SERVER_ERROR);
        }
        return instance(Long.valueOf(String.valueOf(object)));
    }

    /**
     * 根据配置 ID 获取对象存储客户端
     *
     * @param configId 配置 ID
     * @return 对象存储客户端
     */
    public static OssClient instance(Long configId) {
        String json = CacheUtils.get(CacheNames.OSS_CONFIG, configId);
        if (json == null) {
            log.error("配置 ID：{} 对象存储配置不存在", configId);
            throw new CustomException(ResultCodeConstants.SERVER_ERROR);
        }
        OssProperties ossProperties = JsonUtils.parseObject(json, OssProperties.class);
        if (ossProperties == null) {
            log.error("配置 ID：{} 对象存储配置不存在", configId);
            throw new CustomException(ResultCodeConstants.SERVER_ERROR);
        }
        OssClient ossClient = CLIENT_CACHE.get(configId);
        if (ossClient == null) {
            ossClient = new OssClient(configId, ossProperties);
            CLIENT_CACHE.put(ossProperties.getId(), ossClient);
            return CLIENT_CACHE.get(configId);
        }
        return ossClient;
    }
}
