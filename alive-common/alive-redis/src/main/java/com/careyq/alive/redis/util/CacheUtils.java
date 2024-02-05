package com.careyq.alive.redis.util;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Objects;

/**
 * 本地缓存 工具类
 *
 * @author CareyQ
 */
public class CacheUtils {

    private CacheUtils() {
    }

    private static final CacheManager CACHE_MANAGER = SpringUtil.getBean(CacheManager.class);

    /**
     * 保存缓存值
     *
     * @param cacheNames 缓存组名称
     * @param key        缓存key
     * @param value      缓存值
     */
    public static void put(String cacheNames, Object key, Object value) {
        Objects.requireNonNull(CACHE_MANAGER.getCache(cacheNames)).put(key, value);
    }

    /**
     * 获取缓存值
     *
     * @param cacheNames 缓存组名称
     * @param key        缓存key
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(String cacheNames, Object key) {
        Cache.ValueWrapper wrapper = Objects.requireNonNull(CACHE_MANAGER.getCache(cacheNames)).get(key);
        return wrapper != null ? (T) wrapper.get() : null;
    }

    /**
     * 删除缓存值
     *
     * @param cacheNames 缓存组名称
     * @param key        缓存key
     */
    public static void evict(String cacheNames, Object key) {
        Objects.requireNonNull(CACHE_MANAGER.getCache(cacheNames)).evict(key);
    }

    /**
     * 清空缓存值
     *
     * @param cacheNames 缓存组名称
     */
    public static void clear(String cacheNames) {
        Objects.requireNonNull(CACHE_MANAGER.getCache(cacheNames)).clear();
    }
}
