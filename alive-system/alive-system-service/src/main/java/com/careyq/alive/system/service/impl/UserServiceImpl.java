package com.careyq.alive.system.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.system.entity.User;
import com.careyq.alive.system.mapper.UserMapper;
import com.careyq.alive.system.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 系统用户服务实现
 *
 * @author CareyQ
 * @since 2023-11-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public boolean mobileIsExist(String mobile) {
        return this.lambdaQuery()
                .eq(User::getMobile, mobile)
                .exists();
    }

    @Override
    public User getByUsername(String username) {
        return this.lambdaQuery()
                .eq(User::getUsername, username)
                .one();
    }

    @Override
    public String encodePassword(String password) {
        return SecureUtil.sha256(password);
    }

    @Override
    public boolean isPasswordMatch(String password, String encodedPassword) {
        return SecureUtil.sha256(password).equals(encodedPassword);
    }
}
