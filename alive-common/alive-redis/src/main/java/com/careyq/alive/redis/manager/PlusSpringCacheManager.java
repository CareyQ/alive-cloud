package com.careyq.alive.redis.manager;

import com.careyq.alive.redis.util.RedisUtils;
import lombok.Setter;
import org.redisson.api.RMapCache;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonCache;
import org.springframework.boot.convert.DurationStyle;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.transaction.TransactionAwareCacheDecorator;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * A {@link org.springframework.cache.CacheManager} implementation
 * backed by Redisson instance.
 * <p>
 * 修改 RedissonSpringCacheManager 源码
 * 重写 cacheName 处理方法 支持多参数
 *
 * @author Nikita Koksharov
 */

public class PlusSpringCacheManager implements CacheManager {

    @Setter
    private boolean allowNullValues = true;

    @Setter
    private boolean transactionAware = true;

    Map<String, CacheConfig> configMap = new ConcurrentHashMap<>();
    /**
     * 本地缓存（一级缓存）
     */
    ConcurrentMap<String, Cache> instanceMap = new ConcurrentHashMap<>();

    public PlusSpringCacheManager() {
    }

    /**
     * Set cache config mapped by cache name
     *
     * @param config object
     */
    @SuppressWarnings("unchecked")
    public void setConfig(Map<String, ? extends CacheConfig> config) {
        this.configMap = (Map<String, CacheConfig>) config;
    }

    protected CacheConfig createDefaultConfig() {
        return new CacheConfig();
    }

    /**
     * 获取缓存
     *
     * @param name 通过 # 分割，0：name，1：TTL，2：MaxIdleTime，3：MaxSize
     * @return 缓存
     */
    @Override
    public Cache getCache(@NonNull String name) {
        // 重写 cacheName 支持多参数
        String[] array = StringUtils.delimitedListToStringArray(name, "#");
        name = array[0];

        // 本地缓存有，直接返回
        Cache cache = instanceMap.get(name);
        if (cache != null) {
            return cache;
        }

        CacheConfig config = configMap.get(name);
        if (config == null) {
            config = createDefaultConfig();
            configMap.put(name, config);
        }

        if (array.length > 1) {
            config.setTTL(DurationStyle.detectAndParse(array[1]).toMillis());
        }
        if (array.length > 2) {
            config.setMaxIdleTime(DurationStyle.detectAndParse(array[2]).toMillis());
        }
        if (array.length > 3) {
            config.setMaxSize(Integer.parseInt(array[3]));
        }

        return createMapCache(name, config);
    }

    /**
     * 创建 MAP 格式缓存
     *
     * @param name   缓存名称
     * @param config 缓存配置
     * @return 缓存
     */
    private Cache createMapCache(String name, CacheConfig config) {
        RMapCache<Object, Object> map = RedisUtils.getClient().getMapCache(name);

        Cache cache;
        if (config.getMaxIdleTime() == 0 && config.getTTL() == 0 && config.getMaxSize() == 0) {
            cache = new RedissonCache(map, allowNullValues);
        } else {
            cache = new RedissonCache(map, config, allowNullValues);
        }

        if (transactionAware) {
            cache = new TransactionAwareCacheDecorator(cache);
        }
        Cache oldCache = instanceMap.putIfAbsent(name, cache);
        if (oldCache != null) {
            cache = oldCache;
        } else if (config.getMaxSize() != 0) {
            map.setMaxSize(config.getMaxSize());
        }
        return cache;
    }

    @Override
    @NonNull
    public Collection<String> getCacheNames() {
        return Collections.unmodifiableSet(configMap.keySet());
    }

}