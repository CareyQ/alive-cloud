package com.careyq.alive.core.exception;

import com.careyq.alive.core.domain.ResultCode;
import lombok.Data;

/**
 * 文件上传异常
 *
 * @author CareyQ
 */
@Data
public class FileUploadException extends CustomException {

    public FileUploadException(String msg) {
        super(new ResultCode(500, msg));
    }
}
