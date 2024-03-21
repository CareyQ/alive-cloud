package com.careyq.alive.core.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
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
        return coll.stream().map(func).filter(e -> Objects.nonNull(e) && !StrUtil.isEmptyIfStr(e)).collect(Collectors.toSet());
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
        return coll.stream().map(func).filter(e -> Objects.nonNull(e) && !StrUtil.isEmptyIfStr(e)).collect(Collectors.toList());
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
        return coll.stream().map(mapper).flatMap(func).filter(e -> Objects.nonNull(e) && !StrUtil.isEmptyIfStr(e)).collect(Collectors.toList());
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

    public static <T, K> Map<K, List<T>> convertMultiMap(Collection<T> from, Function<T, K> keyFunc) {
        if (CollUtil.isEmpty(from)) {
            return MapUtil.newHashMap();
        }
        return from.stream().collect(Collectors.groupingBy(keyFunc, Collectors.mapping(t -> t, Collectors.toList())));
    }

    /**
     * 获取指定条件第一个元素
     *
     * @param coll      列表
     * @param predicate 条件
     * @param <T>       T
     * @return T
     */
    public static <T> T findFirst(List<T> coll, Predicate<T> predicate) {
        return findFirst(coll, predicate, Function.identity());
    }

    /**
     * 获取指定条件第一个元素
     *
     * @param coll      列表
     * @param predicate 条件
     * @param func      转换逻辑
     * @param <T>       T
     * @return T
     */
    public static <T, U> U findFirst(List<T> coll, Predicate<T> predicate, Function<T, U> func) {
        if (CollUtil.isEmpty(coll)) {
            return null;
        }
        return coll.stream().filter(predicate).findFirst().map(func).orElse(null);
    }

    /**
     * 获取最小值
     *
     * @param coll      列表
     * @param valueFunc 获取值
     * @param <T>       T
     * @param <V>       V
     * @return V
     */
    public static <T, V extends Comparable<? super V>> V getMinValue(List<T> coll, Function<T, V> valueFunc) {
        if (CollUtil.isEmpty(coll)) {
            return null;
        }
        assert !coll.isEmpty();
        T t = coll.stream().min(Comparator.comparing(valueFunc)).get();
        return valueFunc.apply(t);
    }

    /**
     * 指定字段求和
     *
     * @param coll      列表
     * @param valueFunc 获取值
     * @param <T>       T
     * @param <V>       V
     * @return V
     */
    public static <T, V extends Comparable<? super V>> V getSumValue(List<T> coll, Function<T, V> valueFunc,
                                                                     BinaryOperator<V> accumulator) {
        return getSumValue(coll, valueFunc, accumulator, null);
    }

    /**
     * 指定字段求和
     *
     * @param coll         列表
     * @param valueFunc    获取值
     * @param accumulator  累加器
     * @param defaultValue 默认值
     * @param <T>          T
     * @param <V>          V
     * @return V
     */
    public static <T, V extends Comparable<? super V>> V getSumValue(Collection<T> coll, Function<T, V> valueFunc,
                                                                     BinaryOperator<V> accumulator, V defaultValue) {
        if (CollUtil.isEmpty(coll)) {
            return defaultValue;
        }
        assert !coll.isEmpty();
        return coll.stream().map(valueFunc).filter(Objects::nonNull).reduce(accumulator).orElse(defaultValue);
    }
}
