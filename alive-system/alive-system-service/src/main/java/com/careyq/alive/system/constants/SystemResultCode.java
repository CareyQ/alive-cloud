package com.careyq.alive.system.constants;

import com.careyq.alive.core.domain.ResultCode;

/**
 * 错误码常量
 *
 * @author CareyQ
 */
public interface SystemResultCode {

    /**
     * AUTH
     */
    ResultCode AUTH_LOGIN_FAIL = new ResultCode(1_10001, "登录失败，账号密码不正确");
    ResultCode AUTH_LOGIN_DISABLED = new ResultCode(1_10002, "登录失败，账号被禁用");

    /**
     * MENU
     */
    ResultCode MENU_NAME_DUPLICATE = new ResultCode(1_20001, "该菜单名称已存在");
    ResultCode MENU_PARENT_NOT_EXISTS = new ResultCode(1_20002, "父菜单不存在");
    ResultCode MENU_NOT_EXISTS = new ResultCode(1_20003, "该菜单不存在");
    ResultCode MENU_EXISTS_CHILDREN = new ResultCode(1_20004, "还存在子菜单，无法删除");
    ResultCode MENU_PARENT_SELF = new ResultCode(1_20004, "不可以指定自身为父级菜单");

    /**
     * DICT
     */
    ResultCode DICT_TYPE_NAME_DUPLICATE = new ResultCode(1_30001, "已存在相同名称的字典类型");
    ResultCode DICT_TYPE_TYPE_DUPLICATE = new ResultCode(1_30001, "已存在相同类型的字典类型");
    ResultCode DICT_TYPE_NOT_EXISTS = new ResultCode(1_30003, "字典类型不存在");
    ResultCode DICT_TYPE_EXISTS_CHILDREN = new ResultCode(1_30004, "该字典类型还存在字典数据，无法删除");
    ResultCode DICT_DATA_NOT_EXISTS = new ResultCode(1_30005, "该字典数据不存在");
    ResultCode DICT_DATA_LABEL_DUPLICATE = new ResultCode(1_30006, "已存在相同名称的字典数据");

    /**
     * DEPT
     */
    ResultCode DEPT_NOT_EXISTS = new ResultCode(1_40001, "部门不存在");
    ResultCode DEPT_NAME_DUPLICATE = new ResultCode(1_40002, "已存在相同名称的部门");
    ResultCode DEPT_PARENT_NOT_EXISTS = new ResultCode(1_40003, "父级部门不存在");
    ResultCode DEPT_PARENT_ERROR = new ResultCode(1_40004, "父级部门不可选择自己");
    ResultCode DEPT_PARENT_IS_CHILD = new ResultCode(1_40005, "不能设置自己的子部门为父部门");
    ResultCode DEPT_EXISTS_CHILDREN = new ResultCode(1_40006, "还存在子部门，无法删除");
    ResultCode DEPT_HAS_USER = new ResultCode(1_40007, "部门下还存在人员，无法删除");

    /**
     * POST
     */
    ResultCode POST_NOT_EXISTS = new ResultCode(1_50001, "岗位不存在");
    ResultCode POST_NAME_DUPLICATE = new ResultCode(1_50002, "已存在相同名称的岗位");
    ResultCode POST_HAS_USER = new ResultCode(1_50003, "还有人员绑定该岗位，无法删除");

    /**
     * ROLE
     */
    ResultCode ROLE_NOT_EXISTS = new ResultCode(1_50001, "角色不存在");
    ResultCode ROLE_NAME_DUPLICATE = new ResultCode(1_50002, "已存在相同名称的角色");
    ResultCode ROLE_DEFAULT_NOT_EXISTS = new ResultCode(1_50003, "必须存在一个默认角色");

    /**
     * USER
     */
    ResultCode USER_NAME_DUPLICATE = new ResultCode(1_60001, "该用户名已存在");
    ResultCode USER_MOBILE_DUPLICATE = new ResultCode(1_60002, "该手机号已存在");
    ResultCode USER_EMAIL_DUPLICATE = new ResultCode(1_60003, "该邮箱已存在");
    ResultCode USER_NOT_EXISTS = new ResultCode(1_60004, "用户不存在");
    ResultCode USER_STATUS_ALREADY = new ResultCode(1_60005, "用户已经是改状态，请勿重复操作");
    ResultCode USER_PASSWORD_NOT_EMPTY = new ResultCode(1_60006, "密码不能为空");

}
