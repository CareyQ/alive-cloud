package com.careyq.alive.core.util;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
     * 将对象转为 json 字符串
     *
     * @param object 对象
     * @return json 字符串
     */
    @SneakyThrows
    public static String toJson(Object object) {
        if (object == null) {
            return null;
        }
        return objectMapper.writeValueAsString(object);
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

    @SneakyThrows
    public static <T> T parseObject(String text, Class<T> clazz) {
        if (StrUtils.isEmpty(text)) {
            return null;
        }
        return objectMapper.readValue(text, clazz);
    }

    @SneakyThrows
    public static <T> T parseObject(String text, TypeReference<T> typeReference) {
        if (StrUtils.isEmpty(text)) {
            return null;
        }
        return objectMapper.readValue(text, typeReference);
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

    /**
     * 解析数组
     *
     * @param text  json 字符串
     * @param clazz 类
     * @param <T>   泛型
     * @return 数组
     */
    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        if (StrUtil.isEmpty(text)) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(text, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            log.error("json parse err,json:{}", text, e);
            throw new RuntimeException(e);
        }
    }
}
