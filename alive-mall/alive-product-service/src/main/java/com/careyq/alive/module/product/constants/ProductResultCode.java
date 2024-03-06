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
    ResultCode CATEGORY_NAME_IS_EXISTS = new ResultCode(3_10002, "该分类名称已存在");
    ResultCode CATEGORY_PARENT_NOT_ROOT = new ResultCode(3_10003, "父级分类不是顶级分类");
    ResultCode CATEGORY_HAS_CHILDREN = new ResultCode(3_10004, "存在子分类，无法删除");
    /**
     * BRAND
     */
    ResultCode BRAND_NOT_EXISTS = new ResultCode(3_20001, "品牌不存在");
    ResultCode BRAND_NAME_IS_EXISTS = new ResultCode(3_20002, "该品牌名称已存在");
    ResultCode CODEGEN_TABLE_NOT_EXISTS = new ResultCode(3_20003, "表定义不存在");
    ResultCode CODEGEN_COLUMN_NOT_EXISTS = new ResultCode(3_20004, "表字段不存在");
    /**
     * GROUP
     */
    ResultCode ATTRIBUTE_GROUP_NOT_EXISTS = new ResultCode(3_30001, "属性分组不存在");
    ResultCode ATTRIBUTE_GROUP_IS_EXISTS = new ResultCode(3_30002, "该属性分组名称已存在");
    ;

}
