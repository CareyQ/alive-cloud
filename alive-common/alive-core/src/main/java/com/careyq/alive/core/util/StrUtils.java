package com.careyq.alive.core.util;

import cn.hutool.core.util.StrUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 字符串工具类扩展
 *
 * @author CareyQ
 */
public class StrUtils extends StrUtil {

    /**
     * 移除字符串中，包含指定字符串的行
     *
     * @param content  字符串
     * @param sequence 包含的字符串
     * @return 移除后的字符串
     */
    public static String removeLineContains(String content, String sequence) {
        if (StrUtil.isEmpty(content) || StrUtil.isEmpty(sequence)) {
            return content;
        }
        return Arrays.stream(content.split(StrUtil.LF))
                .filter(line -> !line.contains(sequence))
                .collect(Collectors.joining(StrUtil.LF));
    }
}
