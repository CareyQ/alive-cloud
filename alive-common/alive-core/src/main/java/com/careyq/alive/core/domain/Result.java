package com.careyq.alive.core.domain;

import com.careyq.alive.core.constants.ResultCodeConstants;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回体
 *
 * @author CareyQ
 */
@Data
@Builder
public class Result<T> implements Serializable {

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public static <T> Result<T> fail(ResultCode resultCode) {
        return fail(resultCode.code(), resultCode.msg());
    }

    public static <T> Result<T> fail(Integer code, String msg) {
        return Result.<T>builder()
                .code(code)
                .msg(msg)
                .build();
    }

    public static <T> Result<T> ok() {
        return Result.<T>builder()
                .code(ResultCodeConstants.OK.code())
                .msg(ResultCodeConstants.OK.msg())
                .build();
    }

    public static <T> Result<T> ok(T data) {
        Result<T> res = Result.ok();
        res.setData(data);
        return res;
    }

}
