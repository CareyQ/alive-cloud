package com.careyq.alive.module.infra.constants;

import com.careyq.alive.core.domain.ResultCode;

/**
 * 错误码常量
 *
 * @author CareyQ
 */
public interface InfraResultCode {

    /**
     * LOG
     */
    ResultCode ERROR_LOG_NOT_FOUND = new ResultCode(2_10001, "错误日志不存在");
    ResultCode ERROR_LOG_PROCESSED = new ResultCode(2_10002, "错误日志已处理");

    /**
     * DB
     */
    ResultCode DB_CONFIG_NOT_FOUND = new ResultCode(2_20001, "数据源配置不存在");
    ResultCode DB_CONFIG_NAME_DUPLICATE = new ResultCode(2_20002, "数据源配置名称已存在");

    /**
     * CODEGEN
     */
    ResultCode CODEGEN_IMPORT_TABLE_NULL = new ResultCode(3_30001, "导入的表不存在");
    ResultCode CODEGEN_TABLE_COMMENT_IS_NULL = new ResultCode(3_30002, "数据库的表注释未填写");
    ResultCode CODEGEN_IMPORT_COLUMNS_NULL = new ResultCode(3_30003, "导入的字段不存在");
    ResultCode CODEGEN_COLUMN_COMMENT_IS_NULL = new ResultCode(1_30004, "数据库的表字段({})注释未填写");
    ResultCode CODEGEN_DB_TABLE_NOT_EXIST = new ResultCode(1_30005, "数据库的表({})不存在");
    ResultCode CODEGEN_TABLE_IS_EXIST = new ResultCode(1_30006, "数据库的表({})已存在");
    ResultCode CODEGEN_TABLE_NOT_EXISTS = new ResultCode(1_30007, "表定义不存在");
    ResultCode CODEGEN_COLUMN_NOT_EXISTS = new ResultCode(1_30008, "表字段不存在");

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
