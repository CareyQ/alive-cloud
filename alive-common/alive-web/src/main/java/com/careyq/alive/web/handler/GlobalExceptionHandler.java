package com.careyq.alive.web.handler;

import com.careyq.alive.core.domain.R;
import com.careyq.alive.core.exception.CustomException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import static com.careyq.alive.core.constants.ResultCodeConstants.*;

/**
 * 全局异常处理器
 *
 * @author CareyQ
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 请求参数缺失
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R<?> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        log.error("[missingServletRequestParameterExceptionHandler]", ex);
        return R.fail(FAIL_REQUEST.code(), String.format("请求参数缺失: %s", ex.getParameterName()));
    }

    /**
     * 请求参数类型错误
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public R<?> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {
        log.error("[methodArgumentTypeMismatchExceptionHandler]", ex);
        return R.fail(FAIL_REQUEST.code(), String.format("请求参数类型错误: %s", ex.getMessage()));
    }

    /**
     * 参数校验不正确
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<?> methodArgumentNotValidExceptionExceptionHandler(MethodArgumentNotValidException ex) {
        log.error("[methodArgumentNotValidExceptionExceptionHandler]", ex);
        FieldError fieldError = ex.getBindingResult().getFieldError();
        assert fieldError != null;
        return R.fail(FAIL_REQUEST.code(), String.format("请求参数不正确: %s", fieldError.getDefaultMessage()));
    }

    /**
     * Validator 校验参数绑定不正确
     */
    @ExceptionHandler(BindException.class)
    public R<?> bindExceptionHandler(BindException ex) {
        log.error("[handleBindException]", ex);
        FieldError fieldError = ex.getFieldError();
        assert fieldError != null;
        return R.fail(FAIL_REQUEST.code(), String.format("请求参数不正确: %s", fieldError.getDefaultMessage()));
    }

    /**
     * Validator 校验不通过
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public R<?> constraintViolationExceptionHandler(ConstraintViolationException ex) {
        log.error("[constraintViolationExceptionHandler]", ex);
        ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().iterator().next();
        return R.fail(FAIL_REQUEST.code(), String.format("请求参数不正确: %s", constraintViolation.getMessage()));
    }

    /**
     * 请求地址不存在
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public R<?> noHandlerFoundExceptionHandler(NoHandlerFoundException ex) {
        log.error("[noHandlerFoundExceptionHandler]", ex);
        return R.fail(NOT_FOUND.code(), String.format("请求地址不存在: %s", ex.getRequestURL()));
    }

    /**
     * 请求方法不正确
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<?> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        log.error("[httpRequestMethodNotSupportedExceptionHandler]", ex);
        return R.fail(METHOD_NOT_ALLOWED.code(), String.format("请求方法不正确: %s", ex.getMessage()));
    }

    /**
     * 自定义异常
     */
    @ExceptionHandler(CustomException.class)
    public R<?> customExceptionHandler(CustomException ex) {
        log.error("[customExceptionHandler]", ex);
        return R.fail(ex.getCode(), ex.getMessage());
    }

    /**
     * 未捕获到的异常
     */
    @ExceptionHandler(Exception.class)
    public R<?> defaultExceptionHandler(Throwable ex) {
        log.error("[defaultExceptionHandler]", ex);
        return R.fail(SERVER_ERROR.code(), SERVER_ERROR.msg());
    }
}
