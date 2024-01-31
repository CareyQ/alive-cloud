package com.careyq.alive.module.product.constants;

import com.careyq.alive.core.domain.ResultCode;

/**
 * 错误码常量
 *
 * @author CareyQ
 */
public interface ProductResultCode {

    /**
     * CATEGORY
     */
    ResultCode CATEGORY_NOT_EXISTS = new ResultCode(3_10001, "分类不存在");
    ResultCode CATEGORY_NAME_IS_EXISTS = new ResultCode(3_30002, "该分类名称已存在");
    ResultCode CATEGORY_PARENT_NOT_ROOT = new ResultCode(3_30003, "父级分类不是顶级分类");
    ResultCode CATEGORY_HAS_CHILDREN = new ResultCode(3_30004, "存在子分类，无法删除");
    ResultCode CODEGEN_DB_TABLE_NOT_EXIST = new ResultCode(3_30005, "数据库的表({})不存在");
    ResultCode CODEGEN_TABLE_IS_EXIST = new ResultCode(3_30006, "数据库的表({})已存在");
    ResultCode CODEGEN_TABLE_NOT_EXISTS = new ResultCode(3_30007, "表定义不存在");
    ResultCode CODEGEN_COLUMN_NOT_EXISTS = new ResultCode(3_30008, "表字段不存在");
    ;

}
