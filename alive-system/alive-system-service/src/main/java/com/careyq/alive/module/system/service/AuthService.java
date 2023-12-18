package com.careyq.alive.module.system.service;

import com.careyq.alive.module.system.dto.LoginDTO;
import com.careyq.alive.module.system.entity.User;
import com.careyq.alive.module.system.vo.LoginVO;

/**
 * 认证服务
 *
 * @author CareyQ
 */
public interface AuthService {

    /**
     * 验证账号密码
     *
     * @param username 账号
     * @param password 密码
     * @return 结果
     */
    User authenticate(String username, String password);

    /**
     * 账号登录
     *
     * @param dto 登录信息
     * @return 登录结果
     */
    LoginVO login(LoginDTO dto);

    /**
     * 退出登录
     */
    void logout();
}
