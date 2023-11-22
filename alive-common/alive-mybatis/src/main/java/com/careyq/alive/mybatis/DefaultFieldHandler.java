package com.careyq.alive.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.careyq.alive.core.domain.BaseEntity;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 参数填充
 *
 * @author CareyQ
 */
public class DefaultFieldHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity entity) {
            LocalDateTime now = LocalDateTime.now();
            if (Objects.isNull(entity.getCreateTime())) {
                entity.setCreateTime(now);
            }
            if (Objects.isNull(entity.getUpdateTime())) {
                entity.setUpdateTime(now);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object modifyTime = getFieldValByName("updateTime", metaObject);
        if (Objects.isNull(modifyTime)) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}
