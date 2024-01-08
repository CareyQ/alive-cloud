package com.careyq.alive.mybatis.core.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.toolkit.Db;

import java.util.Collection;
import java.util.List;

/**
 * 拓展 MyBatis Plus BaseMapper 类
 *
 * @author CareyQ
 */
public interface BaseMapperX<T> extends BaseMapper<T> {

    /**
     * 扩展 selectList 方法，支持 Lambda 字段
     *
     * @param field 字段
     * @param value 值
     * @return 实体列表
     */
    default List<T> selectList(SFunction<T, ?> field, Object value) {
        return selectList(new LambdaQueryWrapper<T>().eq(field, value));
    }

    /**
     * 批量插入
     *
     * @param entities 实体列表
     */
    default void insertBatch(Collection<T> entities) {
        Db.saveBatch(entities);
    }
}
