package com.careyq.alive.mybatis.core.query;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

/**
 * 拓展 MyBatis Plus QueryWrapper 类
 *
 * @author CareyQ
 */
public class LambdaQueryChainWrapperX<T> extends LambdaQueryChainWrapper<T> {

    public LambdaQueryChainWrapperX(Class<T> entityClass) {
        super(entityClass);
    }

    public LambdaQueryChainWrapperX(BaseMapper<T> baseMapper, Class<T> entityClass) {
        super(baseMapper, entityClass);
    }

    public LambdaQueryChainWrapperX<T> eqIfPresent(SFunction<T, ?> column, Object val) {
        if (ObjectUtil.isNotEmpty(val)) {
            return (LambdaQueryChainWrapperX<T>) super.eq(column, val);
        }
        return this;
    }

    public LambdaQueryChainWrapperX<T> neIfPresent(SFunction<T, ?> column, Object val) {
        if (ObjectUtil.isNotEmpty(val)) {
            return (LambdaQueryChainWrapperX<T>) super.ne(column, val);
        }
        return this;
    }

    public LambdaQueryChainWrapperX<T> inIfPresent(SFunction<T, ?> column, Collection<?> values) {
        if (ObjectUtil.isAllNotEmpty(values) && !ArrayUtil.isEmpty(values)) {
            return (LambdaQueryChainWrapperX<T>) super.in(column, values);
        }
        return this;
    }

    public LambdaQueryChainWrapperX<T> inIfPresent(SFunction<T, ?> column, Object... values) {
        if (ObjectUtil.isAllNotEmpty(values) && !ArrayUtil.isEmpty(values)) {
            return (LambdaQueryChainWrapperX<T>) super.in(column, values);
        }
        return this;
    }

    public LambdaQueryChainWrapperX<T> likeIfPresent(SFunction<T, ?> column, String val) {
        if (StringUtils.hasText(val)) {
            return (LambdaQueryChainWrapperX<T>) super.like(column, val);
        }
        return this;
    }

    public LambdaQueryChainWrapperX<T> dateBetween(SFunction<T, ?> column, LocalDate startDate, LocalDate endDate) {
        if (ObjectUtil.isAllNotEmpty(startDate, endDate)) {
            return (LambdaQueryChainWrapperX<T>) super.between(column, startDate, endDate);
        }
        return this;
    }

    public LambdaQueryChainWrapperX<T> dateTimeBetween(SFunction<T, ?> column, LocalDate startDate, LocalDate endDate) {
        if (ObjectUtil.isAllNotEmpty(startDate, endDate)) {
            return (LambdaQueryChainWrapperX<T>) super.between(column, startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
        }
        return this;
    }
}
