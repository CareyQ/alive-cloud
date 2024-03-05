package com.careyq.alive.redis.util;

import cn.hutool.extra.spring.SpringUtil;
import org.redisson.api.RBatch;
import org.redisson.api.RBucket;
import org.redisson.api.RBucketAsync;
import org.redisson.api.RedissonClient;

import java.time.Duration;

/**
 * Redis 工具类
 *
 * @author CareyQ
 */
public class RedisUtils {

    private RedisUtils() {
    }

    private static final RedissonClient CLIENT = SpringUtil.getBean(RedissonClient.class);

    /**
     * 默认缓存时间，30 分钟
     */
    private static final Long DEFAULT_EXPIRED = 30 * 60L;

    /**
     * 获得客户端
     *
     * @return 客户端
     */
    public static RedissonClient getClient() {
        return CLIENT;
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等，不过期
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public static <T> void setNotExpireObject(final String key, final T value) {
        RBucket<T> bucket = CLIENT.getBucket(key);
        bucket.set(value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public static <T> void setObject(final String key, final T value) {
        setObject(key, value, Duration.ofSeconds(DEFAULT_EXPIRED));
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param duration 时间
     */
    public static <T> void setObject(final String key, final T value, final Duration duration) {
        RBatch batch = CLIENT.createBatch();
        RBucketAsync<T> bucket = batch.getBucket(key);
        bucket.setAsync(value);
        bucket.expireAsync(duration);
        batch.execute();
    }

    /**
     * 获得缓存的基本对象
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public static <T> T getObject(final String key) {
        RBucket<T> rBucket = CLIENT.getBucket(key);
        return rBucket.get();
    }
}
