package com.careyq.alive.mybatis.core.service;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * MyBatis-Plus 服务扩展
 *
 * @author CareyQ
 */
public interface IServiceX<T> extends IService<T> {

    default LambdaQueryChainWrapper<T> lambdaQueryX() {
        return IService.super.lambdaQuery();
    }
}
