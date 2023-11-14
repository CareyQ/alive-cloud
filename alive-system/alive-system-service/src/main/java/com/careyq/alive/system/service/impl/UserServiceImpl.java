package com.careyq.alive.system.service.impl;

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
}
