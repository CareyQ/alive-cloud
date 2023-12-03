package com.careyq.alive.satoken;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaStorage;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.careyq.alive.satoken.core.domain.LoginUser;

/**
 * 登录鉴权助手
 *
 * @author CareyQ
 */
public class AuthHelper {

    public static final String LOGIN_USER = "loginUser";
    public static final String USER_ID = "userId";

    public static SaTokenInfo login(LoginUser loginUser) {
        StpUtil.login(loginUser.getUserId());

        SaStorage storage = SaHolder.getStorage();
        storage.set(LOGIN_USER, loginUser);
        storage.set(USER_ID, loginUser.getUserId());

        StpUtil.getSession().set(LOGIN_USER, loginUser);

        return StpUtil.getTokenInfo();
    }

    public static Long getUserId() {
        Object loginId = StpUtil.getLoginId();
        if (loginId == null) {
          return null;
        }
        return Convert.toLong(loginId);
    }

    public static LoginUser getLoginUser() {
        LoginUser loginUser = (LoginUser) SaHolder.getStorage().get(LOGIN_USER);
        if (loginUser != null) {
            return loginUser;
        }
        SaSession session = StpUtil.getSession();
        if (ObjectUtil.isNull(session)) {
            return null;
        }
        loginUser = (LoginUser) session.get(LOGIN_USER);
        SaHolder.getStorage().set(LOGIN_USER, loginUser);
        return loginUser;
    }
}
