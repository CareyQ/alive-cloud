package com.careyq.alive.core.enums;

import com.careyq.alive.core.domain.EntryVO;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 枚举接口
 *
 * @author CareyQ
 */
public interface IEnum {

    /**
     * 获取枚举编码
     *
     * @return 枚举编码
     */
    Object getCode();

    /**
     * 获取枚举描述
     *
     * @return 枚举描述
     */
    String getDesc();

    /**
     * 根据编码获取枚举
     *
     * @param values 枚举数组
     * @param code   编码
     * @param <T>    枚举类型
     * @return 枚举
     */
    static <T extends IEnum> T codeOf(T[] values, Object code) {
        return Stream.of(values)
                .filter(v -> Objects.equals(v.getCode(), code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("未找到该枚举类型"));
    }

    /**
     * 根据编码获取枚举
     *
     * @param enumType 枚举类型
     * @param code     编码
     * @param <T>      枚举类型
     * @return 枚举
     */
    static <T extends IEnum> T codeOf(Class<T> enumType, Object code) {
        if (!enumType.isEnum()) {
            throw new IllegalArgumentException(enumType.getName() + "不是枚举类");
        }
        return IEnum.codeOf(enumType.getEnumConstants(), code);
    }

    /**
     * 转为 Map
     *
     * @param enumType 枚举类型
     * @return Map
     */
    static Map<Object, String> toMap(Class<? extends IEnum> enumType) {
        return Stream.of(enumType.getEnumConstants())
                .collect(Collectors.toMap(IEnum::getCode, IEnum::getDesc));
    }

    /**
     * 是否存在编码
     *
     * @param enumType 枚举类型
     * @param code     编码
     * @return 是否存在
     */
    static boolean noCode(Class<? extends IEnum> enumType, Object code) {
        return Stream.of(enumType.getEnumConstants())
                .noneMatch(e -> Objects.equals(e.getCode(), code));
    }

    /**
     * 转为记录列表
     *
     * @param values 枚举数组
     * @return 记录列表
     */
    static List<EntryVO> toEntry(IEnum[] values) {
        return Stream.of(values)
                .map(e -> new EntryVO(Long.valueOf(String.valueOf(e.getCode())), e.getDesc()))
                .collect(Collectors.toList());
    }

    /**
     * 转为记录列表
     *
     * @param enumType 枚举类型
     * @return 记录列表
     */
    static List<EntryVO> toEntry(Class<? extends IEnum> enumType) {
        return toEntry(enumType.getEnumConstants());
    }
}
