package com.careyq.alive.core.constants;

import com.careyq.alive.core.domain.ResultCode;

/**
 * 全局错误码常量
 *
 * @author CareyQ
 */
public interface ResultCodeConstants {

    /**
     * 操作成功
     */
    ResultCode OK = new ResultCode(0, "success");

    ResultCode FAIL_REQUEST = new ResultCode(400, "请求参数不正确");
    ResultCode NOT_FOUND = new ResultCode(404, "请求未找到");
    ResultCode METHOD_NOT_ALLOWED = new ResultCode(405, "请求方法不正确");

    ResultCode SERVER_ERROR = new ResultCode(500, "服务器开小差了");
}
