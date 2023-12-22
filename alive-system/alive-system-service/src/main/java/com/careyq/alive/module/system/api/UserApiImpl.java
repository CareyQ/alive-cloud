package com.careyq.alive.module.system.api;

import cn.hutool.core.util.StrUtil;
import com.careyq.alive.module.system.entity.User;
import com.careyq.alive.module.system.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 用户 API 接口实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class UserApiImpl implements UserApi {

    private final UserService userService;

    @Override
    public String getNickname(Long userId) {
        if (userId == null) {
            return StrUtil.EMPTY;
        }
        User user = userService.lambdaQueryX().select(User::getNickname).eq(User::getId, userId).one();
        return Optional.ofNullable(user).orElseGet(User::new).getNickname();
    }
}
