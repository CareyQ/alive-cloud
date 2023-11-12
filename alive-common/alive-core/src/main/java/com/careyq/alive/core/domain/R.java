package com.careyq.alive.core.domain;

import com.careyq.alive.core.constants.ResultCodeConstants;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回体
 *
 * @author CareyQ
 * @since 2023-11-12
 */
@Data
@Builder
public class R<T> implements Serializable {

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

    public static <T> R<T> fail(ResultCode resultCode) {
        return fail(resultCode.code(), resultCode.msg());
    }

    public static <T> R<T> fail(Integer code, String msg) {
        return R.<T>builder()
                .code(code)
                .msg(msg)
                .build();
    }

    public static <T> R<T> ok() {
        return R.<T>builder()
                .code(ResultCodeConstants.OK.code())
                .msg(ResultCodeConstants.OK.msg())
                .build();
    }

    public static <T> R<T> ok(T data) {
        R<T> res = R.ok();
        res.setData(data);
        return res;
    }

}
