package com.careyq.alive.redis;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import lombok.NonNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.time.Duration;

/**
 * cache 支持自定义过期时间
 * 在 {@link Cacheable#cacheNames()} 格式为 "key#ttl" 时，# 后面的 ttl 为过期时间。
 * 单位为最后一个字母（支持的单位有：d 天，h 小时，m 分钟，s 秒），默认单位为 s 秒
 *
 * @author CareyQ
 * @since 2023-11-17
 */
public class TimeoutRedisCacheManager extends RedisCacheManager {

    private static final String SPLIT = "#";

    public TimeoutRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    @NonNull
    @Override
    protected RedisCache createRedisCache(@NonNull String name, RedisCacheConfiguration cacheConfig) {
        if (StrUtil.isEmpty(name)) {
            return super.createRedisCache(name, cacheConfig);
        }
        String[] names = StrUtil.splitToArray(name, SPLIT);
        if (names.length != 2) {
            return super.createRedisCache(name, cacheConfig);
        }
        // 通过修改 cacheConfig 的过期时间，实现自定义过期时间
        if (cacheConfig != null) {
            names[1] = StrUtil.subBefore(names[1], StrUtil.COLON, false);
            cacheConfig = cacheConfig.entryTtl(paresDuration(names[1]));
        }
        return super.createRedisCache(name, cacheConfig);
    }

    /**
     * 解析过期时间
     *
     * @param ttlStr 过期时间字符串
     * @return 过期时间
     */
    private Duration paresDuration(String ttlStr) {
        String timeUnit = StrUtil.subSuf(ttlStr, -1);
        return switch (timeUnit) {
            case "d" -> Duration.ofDays(removeDurationSuffix(ttlStr));
            case "h" -> Duration.ofHours(removeDurationSuffix(ttlStr));
            case "m" -> Duration.ofMinutes(removeDurationSuffix(ttlStr));
            case "s" -> Duration.ofSeconds(removeDurationSuffix(ttlStr));
            default -> Duration.ofSeconds(Long.parseLong(ttlStr));
        };
    }

    /**
     * 移除时间后缀
     *
     * @param ttlStr 过期时间字符串
     * @return 时间
     */
    private Long removeDurationSuffix(String ttlStr) {
        return NumberUtil.parseLong(StrUtil.sub(ttlStr, 0, ttlStr.length() - 1));
    }
}
