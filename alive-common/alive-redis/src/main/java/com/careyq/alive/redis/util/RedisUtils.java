package com.careyq.alive.redis.util;

import com.careyq.alive.core.util.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis 工具类
 *
 * @author CareyQ
 */
@Component
@AllArgsConstructor
public class RedisUtils {

    private static RedisUtils instance;
    private final StringRedisTemplate stringRedisTemplate;
    private final RedissonClient redissonClient;

    /**
     * 默认缓存时间，30 分钟
     */
    private static final long DEFAULT_EXPIRED = 30 * 60L;
    /**
     * 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1L;


    @PostConstruct
    public void init() {
        instance = this;
    }

    /**
     * 获得 Redisson 客户端
     *
     * @return Redisson 客户端
     */
    public static RedissonClient getRedissonClient() {
        return instance.redissonClient;
    }

    /**
     * 获得 redisTemplate
     *
     * @return redisTemplate
     */
    public static StringRedisTemplate getTemplate() {
        return instance.stringRedisTemplate;
    }

    /**
     * 缓存，默认半小时
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public static <T> void set(final String key, final T value) {
        set(key, value, DEFAULT_EXPIRED);
    }

    /**
     * 缓存对象
     *
     * @param key    缓存的键值
     * @param value  缓存的值
     * @param expire 有效期，秒
     */
    public static <T> void set(final String key, final T value, final long expire) {
        set(key, value, expire, TimeUnit.SECONDS);
    }

    /**
     * 缓存对象
     *
     * @param key    缓存的键值
     * @param value  缓存的值
     * @param expire 有效期
     * @param unit   单位
     */
    public static <T> void set(final String key, final T value, final long expire, TimeUnit unit) {
        getTemplate().opsForValue().set(key, JsonUtils.toJson(value));
        if (expire != NOT_EXPIRE) {
            getTemplate().expire(key, expire, unit);
        }
    }

    /**
     * 获得缓存
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public static <T> T get(final String key, Class<T> clazz) {
        Object value = getTemplate().opsForValue().get(key);
        return value == null ? null : JsonUtils.parseObject(value.toString(), clazz);
    }

    /**
     * 获得缓存
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public static <T> T get(final String key, TypeReference<T> typeReference) {
        Object value = getTemplate().opsForValue().get(key);
        return value == null ? null : JsonUtils.parseObject(value.toString(), typeReference);
    }

    /**
     * 缓存是否存在
     *
     * @param key 缓存键值
     * @return 结果
     */
    public static boolean has(final String key) {
        return Boolean.TRUE.equals(getTemplate().hasKey(key));
    }

    /**
     * 删除缓存
     *
     * @param key 缓存键值
     */
    public static void del(final String key) {
        if (has(key)) {
            getTemplate().delete(key);
        }
    }

    /**
     * 获得缓存并删除
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public static <T> T getAndDel(final String key, Class<T> clazz) {
        T t = get(key, clazz);
        del(key);
        return t;
    }

    /**
     * 获得缓存并删除
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public static <T> T getAndDel(final String key, TypeReference<T> typeReference) {
        T t = get(key, typeReference);
        del(key);
        return t;
    }

    /**
     * 自增缓存
     *
     * @param key 缓存键值
     * @return 自增值
     */
    public static Long increment(final String key) {
        return getTemplate().opsForValue().increment(key);
    }

}
