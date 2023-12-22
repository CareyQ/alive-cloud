package com.careyq.alive.module.system.api;

/**
 * 用户 API 接口
 *
 * @author CareyQ
 */
public interface UserApi {

    /**
     * 获取用户名称
     *
     * @param userId 用户编号
     * @return 用户名称
     */
    String getNickname(Long userId);
}
