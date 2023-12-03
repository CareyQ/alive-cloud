package com.careyq.alive.satoken.core.service;

import cn.dev33.satoken.stp.StpInterface;
import com.careyq.alive.satoken.AuthHelper;
import com.careyq.alive.satoken.core.domain.LoginUser;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Sa-Token 自定义权限加载接口实现类
 *
 * @author CareyQ
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        LoginUser loginUser = AuthHelper.getLoginUser();
        assert loginUser != null;
        return loginUser.getPermission();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        LoginUser loginUser = AuthHelper.getLoginUser();
        assert loginUser != null;
        return loginUser.getRole();
    }
}
