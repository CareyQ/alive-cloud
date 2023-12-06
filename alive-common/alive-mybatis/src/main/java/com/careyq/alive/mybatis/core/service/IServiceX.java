package com.careyq.alive.mybatis.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.alive.mybatis.core.mapper.LambdaQueryChainWrapperX;

/**
 * MyBatis-Plus 服务扩展
 *
 * @author CareyQ
 */
public interface IServiceX<T> extends IService<T> {

    default LambdaQueryChainWrapperX<T> lambdaQueryX() {
        return new LambdaQueryChainWrapperX<>(getBaseMapper(), getEntityClass());
    }
}
