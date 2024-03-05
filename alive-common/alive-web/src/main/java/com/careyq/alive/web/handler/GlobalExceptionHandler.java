package com.careyq.alive.web.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.hutool.core.exceptions.ExceptionUtil;
import com.careyq.alive.core.constants.TableNotExists;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.exception.FileUploadException;
import com.careyq.alive.core.util.AsyncUtils;
import com.careyq.alive.core.util.JsonUtils;
import com.careyq.alive.core.util.ServletUtils;
import com.careyq.alive.core.util.TraceUtils;
import com.careyq.alive.module.infra.api.LogApi;
import com.careyq.alive.module.infra.dto.ErrorLogDTO;
import com.careyq.alive.satoken.AuthHelper;
import com.careyq.alive.satoken.core.domain.LoginUser;
import com.careyq.alive.web.util.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Map;

import static com.careyq.alive.core.constants.ResultCodeConstants.*;

/**
 * 全局异常处理器
 *
 * @author CareyQ
 */
@Slf4j
@ResponseStatus(HttpStatus.ACCEPTED)
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final LogApi logApi;

    /**
     * 请求参数缺失
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<?> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        log.error("[missingServletRequestParameterExceptionHandler]", ex);
        return Result.fail(FAIL_REQUEST.code(), String.format("请求参数缺失: %s", ex.getParameterName()));
    }

    /**
     * 请求参数类型错误
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<?> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {
        log.error("[methodArgumentTypeMismatchExceptionHandler]", ex);
        return Result.fail(FAIL_REQUEST.code(), String.format("请求参数类型错误: %s", ex.getMessage()));
    }

    /**
     * multipart 类型错误
     */
    @ExceptionHandler(MultipartException.class)
    public Result<?> multipartExceptionHandler(MultipartException ex) {
        log.error("[multipartExceptionHandler]", ex);
        return Result.fail(FAIL_REQUEST.code(), String.format("请求参数类型错误: %s", ex.getMessage()));
    }

    /**
     * 参数校验不正确
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> methodArgumentNotValidExceptionExceptionHandler(MethodArgumentNotValidException ex) {
        log.error("[methodArgumentNotValidExceptionExceptionHandler]", ex);
        FieldError fieldError = ex.getBindingResult().getFieldError();
        assert fieldError != null;
        return Result.fail(FAIL_REQUEST.code(), String.format("请求参数不正确: %s", fieldError.getDefaultMessage()));
    }

    /**
     * Validator 校验参数绑定不正确
     */
    @ExceptionHandler(BindException.class)
    public Result<?> bindExceptionHandler(BindException ex) {
        log.error("[handleBindException]", ex);
        FieldError fieldError = ex.getFieldError();
        assert fieldError != null;
        return Result.fail(FAIL_REQUEST.code(), String.format("请求参数不正确: %s", fieldError.getDefaultMessage()));
    }

    /**
     * Validator 校验不通过
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<?> constraintViolationExceptionHandler(ConstraintViolationException ex) {
        log.error("[constraintViolationExceptionHandler]", ex);
        ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().iterator().next();
        return Result.fail(FAIL_REQUEST.code(), String.format("请求参数不正确: %s", constraintViolation.getMessage()));
    }

    /**
     * 请求地址不存在
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<?> noHandlerFoundExceptionHandler(NoHandlerFoundException ex) {
        log.error("[noHandlerFoundExceptionHandler]", ex);
        return Result.fail(NOT_FOUND.code(), String.format("请求地址不存在: %s", ex.getRequestURL()));
    }

    /**
     * 请求方法不正确
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        log.error("[httpRequestMethodNotSupportedExceptionHandler]", ex);
        return Result.fail(METHOD_NOT_ALLOWED.code(), String.format("请求方法不正确: %s", ex.getMessage()));
    }

    /**
     * sa-token 认证异常拦截
     */
    @ExceptionHandler(NotLoginException.class)
    public Result<?> notLoginExceptionHandler(NotLoginException ex) {
        log.error("[notLoginExceptionHandler], msg:{}", ex.getMessage());
        return Result.fail(UNAUTHORIZED);
    }

    /**
     * sa-token 权限异常拦截
     */
    @ExceptionHandler(NotPermissionException.class)
    public Result<?> notPermissionExceptionHandler(HttpServletRequest req, NotPermissionException ex) {
        log.error("[notPermissionExceptionHandler][userId({}) url({})]", AuthHelper.getUserId(), req.getRequestURL(), ex);
        return Result.fail(FORBIDDEN);
    }

    /**
     * 自定义异常
     */
    @ExceptionHandler(CustomException.class)
    public Result<?> customExceptionHandler(CustomException ex) {
        log.error("[customExceptionHandler]", ex);
        return Result.fail(ex.getCode(), ex.getMessage());
    }

    /**
     * 文件上传异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FileUploadException.class)
    public Result<?> fileUploadExceptionHandler(FileUploadException ex) {
        log.error("[fileUploadExceptionHandler]", ex);
        return Result.fail(ex.getCode(), ex.getMessage());
    }

    /**
     * 未捕获到的异常
     */
    @ExceptionHandler(Exception.class)
    public Result<?> defaultExceptionHandler(HttpServletRequest req, Throwable ex) {
        Result<?> result = this.handleTableNotExists(ex);
        if (result != null) {
            return result;
        }
        log.error("[defaultExceptionHandler]", ex);
        AsyncUtils.runAsync(() -> this.createErrorLog(req, ex));
        return Result.fail(SERVER_ERROR.code(), SERVER_ERROR.msg());
    }

    private Result<?> handleTableNotExists(Throwable ex) {
        String message = ExceptionUtil.getRootCauseMessage(ex);
        if (!message.contains(TableNotExists.TABLE_NOT_EXIST)) {
            return null;
        }
        if (message.contains(TableNotExists.TABLE_SYSTEM)) {
            log.error("[system 模块 alive-system - 表结构未导入]");
            return Result.fail(NOT_IMPLEMENTED.code(), "[system 模块 alive-system - 表结构未导入]");
        }
        if (message.contains(TableNotExists.TABLE_INFRA)) {
            log.error("[system 模块 alive-infra - 表结构未导入]");
            return Result.fail(NOT_IMPLEMENTED.code(), "[system 模块 alive-infra - 表结构未导入]");
        }
        if (message.contains(TableNotExists.TABLE_MALL)) {
            log.error("[system 模块 alive-mall - 表结构未导入]");
            return Result.fail(NOT_IMPLEMENTED.code(), "[system 模块 alive-mall - 表结构未导入]");
        }
        return null;
    }

    private void createErrorLog(HttpServletRequest request, Throwable e) {
        // 插入错误日志
        ErrorLogDTO errorLog = new ErrorLogDTO();
        try {
            // 初始化 errorLog
            initErrorLog(errorLog, request, e);
            // 执行插入 errorLog
            logApi.createErrorLog(errorLog);
        } catch (Throwable th) {
            log.error("[createExceptionLog][url({}) log({}) 发生异常]", request.getRequestURI(), JsonUtils.toJson(errorLog), th);
        }
    }

    private void initErrorLog(ErrorLogDTO errorLog, HttpServletRequest request, Throwable e) {
        // 处理用户信息
        LoginUser loginUser = AuthHelper.getLoginUser();
        errorLog.setNickname(loginUser.getNickname());
        errorLog.setUserType(WebUtils.getLoginUserType(request));
        // 设置异常字段
        errorLog.setExName(e.getClass().getName());
        errorLog.setExMessage(ExceptionUtil.getMessage(e));
        errorLog.setExRootCauseMessage(ExceptionUtil.getRootCauseMessage(e));
        errorLog.setExStackTrace(ExceptionUtils.getStackTrace(e));
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        Assert.notEmpty(stackTraceElements, "异常 stackTraceElements 不能为空");
        // 设置其它字段
        errorLog.setTraceId(TraceUtils.getTraceId());
        errorLog.setRequestUrl(request.getRequestURI());
        Map<String, Object> requestParams = Map.of("query", ServletUtils.getParamMap(request),
                "body", ServletUtils.getBody(request));
        errorLog.setRequestParams(JsonUtils.toJson(requestParams));
        errorLog.setRequestMethod(request.getMethod());
        errorLog.setRequestCurl(ServletUtils.getCurl(request));
        errorLog.setDevice(ServletUtils.getUserAgentInfo(request));
        errorLog.setIp(ServletUtils.getClientIP(request));
    }
}
