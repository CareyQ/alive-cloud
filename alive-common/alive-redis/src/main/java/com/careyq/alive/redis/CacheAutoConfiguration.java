package com.careyq.alive.redis;

import cn.hutool.core.util.StrUtil;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.util.Objects;

import static com.careyq.alive.redis.RedisAutoConfiguration.buildRedisSerializer;

/**
 * Cache 配置
 *
 * @author CareyQ
 * @since 2023-11-17
 */
@EnableCaching
@AutoConfiguration
@EnableConfigurationProperties({CacheProperties.class})
public class CacheAutoConfiguration {

    @Bean
    @Primary
    public RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        config = config.computePrefixWith(cacheName -> cacheName + StrUtil.COLON);
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(buildRedisSerializer()));

        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        if (redisProperties.getKeyPrefix() != null) {
            config = config.prefixCacheNameWith(redisProperties.getKeyPrefix());
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }
        return config;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate<String, Object> redisTemplate, RedisCacheConfiguration redisCacheConfiguration) {
        RedisConnectionFactory factory = Objects.requireNonNull(redisTemplate.getConnectionFactory());
        RedisCacheWriter cacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(factory);
        return new TimeoutRedisCacheManager(cacheWriter, redisCacheConfiguration);
    }
}
