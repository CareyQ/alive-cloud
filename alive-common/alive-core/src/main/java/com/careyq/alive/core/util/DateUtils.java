package com.careyq.alive.core.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * 时间日期工具类扩展
 *
 * @author CareyQ
 */
public class DateUtils {

    DateTimeFormatter defaultFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public static LocalDateTime atStartOfDay(LocalDate localDate) {
        if (Objects.isNull(localDate)) {
            return null;
        }
        return localDate.atTime(0, 0, 0);
    }

    public static LocalDateTime atEndOfDay(LocalDate localDate) {
        if (Objects.isNull(localDate)) {
            return null;
        }
        return localDate.atTime(23, 59, 59, 99);
    }
}
