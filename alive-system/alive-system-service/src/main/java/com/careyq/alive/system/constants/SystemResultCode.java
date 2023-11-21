package com.careyq.alive.system.constants;

import com.careyq.alive.core.domain.ResultCode;

/**
 * 错误码常量
 *
 * @author CareyQ
 * @since 2023-11-19
 */
public interface SystemResultCode {

    // AUTH
    ResultCode AUTH_LOGIN_FAIL = new ResultCode(1_10001, "登录失败，账号密码不正确");
    ResultCode AUTH_LOGIN_DISABLED = new ResultCode(1_10001, "登录失败，账号被禁用");

    // MENU
    ResultCode MENU_NAME_DUPLICATE = new ResultCode(1_20001, "该菜单名称已存在");
    ResultCode MENU_PARENT_NOT_EXISTS = new ResultCode(1_20002, "父菜单不存在");
    ResultCode MENU_NOT_EXISTS = new ResultCode(1_20003, "该菜单不存在");

}
