package com.careyq.alive.core.util;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * JSON 工具类
 *
 * @author CareyQ
 */
@Slf4j
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModules(new JavaTimeModule());
    }

    /**
     * 初始化 objectMapper，使用 bean
     */
    public static void init(ObjectMapper objectMapper) {
        JsonUtils.objectMapper = objectMapper;
        log.info("[alive][init][初始化 JsonUtils 成功]");
    }

    @SneakyThrows
    public static byte[] toJsonByte(Object object) {
        return objectMapper.writeValueAsBytes(object);
    }

    public static JsonNode parseObject(String text) {
        if (StrUtil.isEmpty(text)) {
            return null;
        }
        try {
            return objectMapper.readTree(text);
        } catch (IOException e) {
            log.error("json parse err,json:{}", text, e);
            throw new RuntimeException(e);
        }
    }
}
