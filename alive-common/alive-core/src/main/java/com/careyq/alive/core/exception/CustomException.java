package com.careyq.alive.core.exception;

import com.careyq.alive.core.domain.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 自定义异常
 *
 * @author CareyQ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomException extends RuntimeException {

    private Integer code;

    private String message;

    public CustomException(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.msg();
    }
}
