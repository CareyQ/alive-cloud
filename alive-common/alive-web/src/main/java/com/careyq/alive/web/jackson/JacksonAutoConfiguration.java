package com.careyq.alive.web.jackson;

import cn.hutool.core.collection.CollUtil;
import com.careyq.alive.core.util.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

/**
 * jackson 序列化配置
 *
 * @author CareyQ
 * @since 2023-11-17
 */
@Slf4j
@AutoConfiguration
public class JacksonAutoConfiguration {

    @Bean
    @SuppressWarnings("InstantiationOfUtilityClass")
    public JsonUtils jsonUtils(List<ObjectMapper> objectMappers) {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, NumberSerializer.INSTANCE)
                .addSerializer(Long.TYPE, NumberSerializer.INSTANCE)
                .addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE)
                .addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);
        objectMappers.forEach(objectMapper -> objectMapper.registerModule(simpleModule));
        JsonUtils.init(CollUtil.getFirst(objectMappers));
        return new JsonUtils();
    }
}
