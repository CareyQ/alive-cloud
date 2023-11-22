package com.careyq.alive.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.alive.system.entity.User;

/**
 * 系统用户服务
 *
 * @author CareyQ
 */
public interface UserService extends IService<User> {

    /**
     * 手机号是否已存在
     *
     * @param mobile 手机号
     * @return 是否已存在
     */
    boolean mobileIsExist(String mobile);

    /**
     * 根据用户名获取
     *
     * @param username 用户名
     * @return 用户
     */
    User getByUsername(String username);

    /**
     * 密码加密
     *
     * @param password 密码
     * @return 解密后的密码
     */
    String encodePassword(String password);

    /**
     * 判断密码是否匹配
     *
     * @param password        未加密密码
     * @param encodedPassword 加密后的密码
     * @return 结果
     */
    boolean isPasswordMatch(String password, String encodedPassword);
}
