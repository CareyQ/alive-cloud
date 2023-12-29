package com.careyq.alive.mybatis.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.mybatis.core.query.LambdaQueryChainWrapperX;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * MyBatis-Plus 服务扩展
 *
 * @author CareyQ
 */
public interface IServiceX<T> extends IService<T> {

    /**
     * 使用重写的 LambdaQueryChainWrapperX
     *
     * @return LambdaQueryChainWrapperX
     */
    default LambdaQueryChainWrapperX<T> lambdaQueryX() {
        return new LambdaQueryChainWrapperX<>(getBaseMapper(), getEntityClass());
    }

    /**
     * 扩展原有的 listByIds 方法，当 ID 列表为空时，返回空列表
     *
     * @param ids 主键ID列表
     * @return 实体列表
     */
    @Override
    default List<T> listByIds(Collection<? extends Serializable> ids) {
        if (CollUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }
        return getBaseMapper().selectBatchIds(ids);
    }
}
