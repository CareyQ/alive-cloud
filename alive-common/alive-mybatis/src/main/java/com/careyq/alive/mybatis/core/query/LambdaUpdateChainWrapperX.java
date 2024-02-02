package com.careyq.alive.mybatis.core.query;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

/**
 * 拓展 MyBatis Plus QueryWrapper 类
 *
 * @author CareyQ
 */
public class LambdaUpdateChainWrapperX<T> extends LambdaUpdateChainWrapper<T> {

    public LambdaUpdateChainWrapperX(BaseMapper<T> baseMapper) {
        super(baseMapper);
    }

    public LambdaUpdateChainWrapperX(Class<T> entityClass) {
        super(entityClass);
    }

    public LambdaUpdateChainWrapperX<T> eqIfPresent(SFunction<T, ?> column, Object val) {
        if (ObjectUtil.isNotEmpty(val)) {
            return (LambdaUpdateChainWrapperX<T>) super.eq(column, val);
        }
        return this;
    }

    public LambdaUpdateChainWrapperX<T> neIfPresent(SFunction<T, ?> column, Object val) {
        if (ObjectUtil.isNotEmpty(val)) {
            return (LambdaUpdateChainWrapperX<T>) super.ne(column, val);
        }
        return this;
    }

    public LambdaUpdateChainWrapperX<T> inIfPresent(SFunction<T, ?> column, Collection<?> values) {
        if (ObjectUtil.isAllNotEmpty(values) && !ArrayUtil.isEmpty(values)) {
            return (LambdaUpdateChainWrapperX<T>) super.in(column, values);
        }
        return this;
    }

    public LambdaUpdateChainWrapperX<T> inIfPresent(SFunction<T, ?> column, Object... values) {
        if (ObjectUtil.isAllNotEmpty(values) && !ArrayUtil.isEmpty(values)) {
            return (LambdaUpdateChainWrapperX<T>) super.in(column, values);
        }
        return this;
    }

    public LambdaUpdateChainWrapperX<T> likeIfPresent(SFunction<T, ?> column, String val) {
        if (StringUtils.hasText(val)) {
            return (LambdaUpdateChainWrapperX<T>) super.like(column, val);
        }
        return this;
    }

    public LambdaUpdateChainWrapperX<T> dateBetween(SFunction<T, ?> column, LocalDate startDate, LocalDate endDate) {
        if (ObjectUtil.isAllNotEmpty(startDate, endDate)) {
            return (LambdaUpdateChainWrapperX<T>) super.between(column, startDate, endDate);
        }
        return this;
    }

    public LambdaUpdateChainWrapperX<T> dateTimeBetween(SFunction<T, ?> column, LocalDate startDate, LocalDate endDate) {
        if (ObjectUtil.isAllNotEmpty(startDate, endDate)) {
            return (LambdaUpdateChainWrapperX<T>) super.between(column, startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
        }
        return this;
    }
}
