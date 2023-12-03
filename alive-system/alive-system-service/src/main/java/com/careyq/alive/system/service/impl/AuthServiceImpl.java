package com.careyq.alive.system.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.careyq.alive.core.enums.CommonStatusEnum;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.satoken.AuthHelper;
import com.careyq.alive.satoken.core.domain.LoginUser;
import com.careyq.alive.system.dto.LoginDTO;
import com.careyq.alive.system.entity.User;
import com.careyq.alive.system.service.AuthService;
import com.careyq.alive.system.service.PermissionService;
import com.careyq.alive.system.service.UserService;
import com.careyq.alive.system.vo.LoginVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.careyq.alive.system.constants.SystemResultCode.AUTH_LOGIN_DISABLED;
import static com.careyq.alive.system.constants.SystemResultCode.AUTH_LOGIN_FAIL;

/**
 * 认证服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PermissionService permissionService;
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
        if (CommonStatusEnum.DISABLE.getStatus().equals(user.getStatus())) {
            throw new CustomException(AUTH_LOGIN_DISABLED);
        }
        return user;
    }

    @Override
    public LoginVO login(LoginDTO dto) {
        User user = this.authenticate(dto.getUsername(), dto.getPassword());

        LoginUser loginUser = this.buildLoginUser(user);
        SaTokenInfo tokenInfo = AuthHelper.login(loginUser);

        return LoginVO.builder()
                .userId(user.getId())
                .accessToken(tokenInfo.tokenValue)
                .build();
    }

    private LoginUser buildLoginUser(User user) {
        // 角色
        List<String> userRole = permissionService.getUserRole(user.getId());
        // 权限
        List<String> userPermission = permissionService.getUserPermission(user.getId());
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(user.getId())
                .setDeptId(user.getDeptId())
                .setPermission(userPermission)
                .setRole(userRole)
                .setUsername(user.getUsername())
                .setNickname(user.getNickname());
        return loginUser;
    }

    @Override
    public void logout() {
        StpUtil.logout();
    }
}
