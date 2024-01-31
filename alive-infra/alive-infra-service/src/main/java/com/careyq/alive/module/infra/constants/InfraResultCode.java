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
    ResultCode CODEGEN_IMPORT_TABLE_NULL = new ResultCode(2_30001, "导入的表不存在");
    ResultCode CODEGEN_TABLE_COMMENT_IS_NULL = new ResultCode(2_30002, "数据库的表注释未填写");
    ResultCode CODEGEN_IMPORT_COLUMNS_NULL = new ResultCode(2_30003, "导入的字段不存在");
    ResultCode CODEGEN_COLUMN_COMMENT_IS_NULL = new ResultCode(2_30004, "数据库的表字段({})注释未填写");
    ResultCode CODEGEN_DB_TABLE_NOT_EXIST = new ResultCode(2_30005, "数据库的表({})不存在");
    ResultCode CODEGEN_TABLE_IS_EXIST = new ResultCode(2_30006, "数据库的表({})已存在");
    ResultCode CODEGEN_TABLE_NOT_EXISTS = new ResultCode(2_30007, "表定义不存在");
    ResultCode CODEGEN_COLUMN_NOT_EXISTS = new ResultCode(2_30008, "表字段不存在");
    ;

}
