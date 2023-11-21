package com.careyq.alive.system.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.careyq.alive.core.enums.CommonStatusEnum;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.system.dto.LoginDTO;
import com.careyq.alive.system.entity.User;
import com.careyq.alive.system.service.AuthService;
import com.careyq.alive.system.service.UserService;
import com.careyq.alive.system.vo.LoginVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.careyq.alive.system.constants.SystemResultCode.AUTH_LOGIN_DISABLED;
import static com.careyq.alive.system.constants.SystemResultCode.AUTH_LOGIN_FAIL;

/**
 * 认证服务实现
 *
 * @author CareyQ
 * @since 2023-11-19
 */
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Override
    public User authenticate(String username, String password) {
        User user = userService.getByUsername(username);
        if (user == null) {
            throw new CustomException(AUTH_LOGIN_FAIL);
        }
        if (!userService.isPasswordMatch(password, user.getPassword())) {
            throw new CustomException(AUTH_LOGIN_FAIL);
        }
        if (!CommonStatusEnum.DISABLE.getStatus().equals(user.getStatus())) {
            throw new CustomException(AUTH_LOGIN_DISABLED);
        }
        return user;
    }

    @Override
    public LoginVO login(LoginDTO dto) {
        User user = this.authenticate(dto.getUsername(), dto.getPassword());

        StpUtil.login(user.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        return LoginVO.builder()
                .userId(user.getId())
                .accessToken(tokenInfo.tokenValue)
                .build();
    }
}
