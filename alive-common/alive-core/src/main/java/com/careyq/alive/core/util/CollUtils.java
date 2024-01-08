package com.careyq.alive.core.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 集合工具类扩展
 *
 * @author CareyQ
 */
public class CollUtils extends CollUtil {

    /**
     * 获取列表中的某个字段并转为 SET
     *
     * @param coll 列表
     * @param func 获取字段
     * @return List
     */
    public static <T, U> Set<U> convertSet(Collection<T> coll, Function<T, U> func) {
        if (isEmpty(coll)) {
            return new HashSet<>();
        }
        return coll.stream().map(func).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    /**
     * 转换为指定内容的 List
     *
     * @param coll 列表
     * @param func 转换逻辑
     * @return List
     */
    public static <T, U> List<U> convertList(Collection<T> coll, Function<T, U> func) {
        if (isEmpty(coll)) {
            return new ArrayList<>();
        }
        return coll.stream().map(func).filter(e -> Objects.nonNull(e) && StrUtil.isEmptyIfStr(e)).collect(Collectors.toList());
    }

    /**
     * 转换为指定内容的 List
     *
     * @param coll 列表
     * @param func 转换逻辑
     * @return List
     */
    public static <T, U, R> List<R> convertFlatList(Collection<T> coll, Function<? super T, ? extends U> mapper, Function<U, ? extends Stream<? extends R>> func) {
        if (isEmpty(coll)) {
            return new ArrayList<>();
        }
        return coll.stream().map(mapper).flatMap(func).filter(e -> Objects.nonNull(e) && StrUtil.isEmptyIfStr(e)).collect(Collectors.toList());
    }

    /**
     * 获取列表为 MAP
     *
     * @param coll 列表
     * @param func 获取字段
     * @return map
     */
    public static <T, K> Map<K, T> convertMap(Collection<T> coll, Function<T, K> func) {
        if (isEmpty(coll)) {
            return MapUtil.newHashMap();
        }
        return coll.stream().collect(Collectors.toMap(func, Function.identity(), (k1, k2) -> k1));
    }

    /**
     * 获取列表为 MAP
     *
     * @param coll      列表
     * @param keyFunc   键
     * @param valueFunc 值
     * @return map
     */
    public static <T, K, V> Map<K, V> convertMap(Collection<T> coll, Function<T, K> keyFunc, Function<T, V> valueFunc) {
        if (isEmpty(coll)) {
            return MapUtil.newHashMap();
        }
        return coll.stream().collect(Collectors.toMap(keyFunc, valueFunc, (v1, v2) -> v1));
    }
}
