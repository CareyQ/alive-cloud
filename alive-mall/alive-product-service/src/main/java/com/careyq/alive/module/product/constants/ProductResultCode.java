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
    ResultCode CATEGORY_NOT_EXISTS = new ResultCode(3_10001, "商品分类不存在");
    ResultCode CATEGORY_NAME_IS_EXISTS = new ResultCode(3_10002, "该商品分类名称已存在");
    ResultCode CATEGORY_PARENT_NOT_ROOT = new ResultCode(3_10003, "父级分类不是顶级分类");
    ResultCode CATEGORY_HAS_CHILDREN = new ResultCode(3_10004, "存在子分类，无法删除");
    ResultCode CATEGORY_DISABLED = new ResultCode(3_10005, "商品分类[{}]已禁用");
    /**
     * BRAND
     */
    ResultCode BRAND_NOT_EXISTS = new ResultCode(3_20001, "商品品牌不存在");
    ResultCode BRAND_NAME_IS_EXISTS = new ResultCode(3_20002, "该商品品牌名称已存在");
    ResultCode BRAND_DISABLED = new ResultCode(3_10005, "商品品牌[{}]已禁用");

    /**
     * ATTRIBUTE_GROUP
     */
    ResultCode ATTRIBUTE_GROUP_NOT_EXISTS = new ResultCode(3_30001, "属性分组不存在");
    ResultCode ATTRIBUTE_GROUP_IS_EXISTS = new ResultCode(3_30002, "该属性分组名称已存在");
    /**
     * ATTRIBUTE
     */
    ResultCode ATTRIBUTE_NOT_EXISTS = new ResultCode(3_30001, "属性不存在");
    ResultCode ATTRIBUTE_IS_EXISTS = new ResultCode(3_30002, "该属性名称已存在");
    /**
     * PRODUCT
     */
    ResultCode PRODUCT_NOT_EXISTS = new ResultCode(3_30001, "商品不存在");
    ResultCode PRODUCT_IS_EXISTS = new ResultCode(3_30002, "该商品编码已存在");
    /**
     * SKU
     */
    ResultCode SKU_NOT_EXISTS = new ResultCode(3_40001, "SKU 信息不存在");
    ResultCode SKU_SPEC_DUPLICATED = new ResultCode(3_40002, "SKU 规格信息重复");
    ResultCode SKU_SPEC_NOT_EQUALS = new ResultCode(3_40003, "SKU 规格信息不一致");
}
