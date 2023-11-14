package com.careyq.alive.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.alive.system.entity.User;

/**
 * 系统用户服务
 *
 * @author CareyQ
 * @since 2023-11-14
 */
public interface UserService extends IService<User> {

    /**
     * 手机号是否已存在
     *
     * @param mobile 手机号
     * @return 是否已存在
     */
    boolean mobileIsExist(String mobile);

}
