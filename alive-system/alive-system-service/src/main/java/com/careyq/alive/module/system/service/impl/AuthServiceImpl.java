package com.careyq.alive.module.system.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.careyq.alive.core.enums.CommonStatusEnum;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.util.ServletUtils;
import com.careyq.alive.core.util.TraceUtils;
import com.careyq.alive.module.infra.api.LogApi;
import com.careyq.alive.module.infra.dto.LoginLogDTO;
import com.careyq.alive.module.infra.enums.LoginLogTypeEnum;
import com.careyq.alive.module.system.constants.SystemResultCode;
import com.careyq.alive.module.system.service.PermissionService;
import com.careyq.alive.module.system.service.UserService;
import com.careyq.alive.satoken.AuthHelper;
import com.careyq.alive.satoken.core.domain.LoginUser;
import com.careyq.alive.module.system.convert.UserConvert;
import com.careyq.alive.module.system.dto.LoginDTO;
import com.careyq.alive.module.system.entity.User;
import com.careyq.alive.module.system.enums.LoginResultEnum;
import com.careyq.alive.module.system.service.AuthService;
import com.careyq.alive.module.system.vo.LoginVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private final LogApi logApi;

    @Override
    public User authenticate(String username, String password) {
        LoginLogTypeEnum logType = LoginLogTypeEnum.LOGIN_USERNAME;
        User user = userService.getByUsername(username);
        if (user == null) {
            this.createLoginLog(null, username, logType, LoginResultEnum.BAD_CREDENTIALS);
            throw new CustomException(SystemResultCode.AUTH_LOGIN_FAIL);
        }
        if (!userService.isPasswordMatch(password, user.getPassword())) {
            this.createLoginLog(null, username, logType, LoginResultEnum.BAD_CREDENTIALS);
            throw new CustomException(SystemResultCode.AUTH_LOGIN_FAIL);
        }
        if (CommonStatusEnum.DISABLE.getStatus().equals(user.getStatus())) {
            this.createLoginLog(null, username, logType, LoginResultEnum.USER_DISABLED);
            throw new CustomException(SystemResultCode.AUTH_LOGIN_DISABLED);
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
        LoginLogDTO loginLog = new LoginLogDTO();
        loginLog.setType(loginLogType.getType())
                .setTraceId(TraceUtils.getTraceId())
                .setUserId(userId)
                .setUsername(username)
                .setResult(loginResult.getType())
                .setIp(clientIp)
                .setIpInfo(ServletUtils.getIpInfo(clientIp))
                .setDevice(ServletUtils.getUserAgentInfo());
        logApi.createLoginLog(loginLog);
        if (userId != null && LoginResultEnum.SUCCESS.equals(loginResult)) {
            userService.updateLoginTime(userId, loginLog.getIp());
        }
    }
}
