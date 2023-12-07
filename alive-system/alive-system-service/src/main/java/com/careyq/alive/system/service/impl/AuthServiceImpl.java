package com.careyq.alive.system.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.careyq.alive.core.enums.CommonStatusEnum;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.util.ServletUtils;
import com.careyq.alive.core.util.TraceUtils;
import com.careyq.alive.satoken.AuthHelper;
import com.careyq.alive.satoken.core.domain.LoginUser;
import com.careyq.alive.system.convert.UserConvert;
import com.careyq.alive.system.dto.LoginDTO;
import com.careyq.alive.system.entity.LoginLog;
import com.careyq.alive.system.entity.User;
import com.careyq.alive.system.enums.LoginLogTypeEnum;
import com.careyq.alive.system.enums.LoginResultEnum;
import com.careyq.alive.system.service.AuthService;
import com.careyq.alive.system.service.LoginLogService;
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
    private final LoginLogService loginLogService;

    @Override
    public User authenticate(String username, String password) {
        LoginLogTypeEnum logType = LoginLogTypeEnum.LOGIN_USERNAME;
        User user = userService.getByUsername(username);
        if (user == null) {
            this.createLoginLog(null, username, logType, LoginResultEnum.BAD_CREDENTIALS);
            throw new CustomException(AUTH_LOGIN_FAIL);
        }
        if (!userService.isPasswordMatch(password, user.getPassword())) {
            this.createLoginLog(null, username, logType, LoginResultEnum.BAD_CREDENTIALS);
            throw new CustomException(AUTH_LOGIN_FAIL);
        }
        if (CommonStatusEnum.DISABLE.getStatus().equals(user.getStatus())) {
            this.createLoginLog(null, username, logType, LoginResultEnum.USER_DISABLED);
            throw new CustomException(AUTH_LOGIN_DISABLED);
        }
        return user;
    }

    @Override
    public LoginVO login(LoginDTO dto) {
        User user = this.authenticate(dto.getUsername(), dto.getPassword());

        LoginUser loginUser = this.buildLoginUser(user);
        SaTokenInfo tokenInfo = AuthHelper.login(loginUser);

        this.createLoginLog(user.getId(), user.getUsername(), LoginLogTypeEnum.LOGIN_USERNAME, LoginResultEnum.SUCCESS);
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
        return UserConvert.INSTANCE.convertToLoginUser(user, userRole, userPermission);
    }

    @Override
    public void logout() {
        StpUtil.logout();
    }

    /**
     * 创建登录日志
     *
     * @param userId       用户 ID
     * @param username     用户名
     * @param loginLogType 登录类型
     * @param loginResult  登录结果
     */
    private void createLoginLog(Long userId, String username, LoginLogTypeEnum loginLogType, LoginResultEnum loginResult) {
        String clientIp = ServletUtils.getClientIp();
        LoginLog loginLog = new LoginLog();
        loginLog.setType(loginLogType.getType())
                .setTraceId(TraceUtils.getTraceId())
                .setUserId(userId)
                .setUsername(username)
                .setResult(loginResult.getType())
                .setIp(clientIp)
                .setIpInfo(ServletUtils.getIpInfo(clientIp))
                .setDevice(ServletUtils.getUserAgentInfo());
        loginLogService.saveLoginLog(loginLog);
        if (userId != null && LoginResultEnum.SUCCESS.equals(loginResult)) {
            userService.updateLoginTime(userId, loginLog.getIp());
        }
    }
}
