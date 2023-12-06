package com.careyq.alive.operatelog.core.aop;

import cn.hutool.core.util.ArrayUtil;
import com.careyq.alive.core.enums.UserTypeEnum;
import com.careyq.alive.operatelog.core.annotations.OperateLog;
import com.careyq.alive.operatelog.core.domain.OperateLogRecord;
import com.careyq.alive.web.util.WebUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * 拦截使用 @OperateLog 注解，如果满足条件，则生成操作日志
 * 满足如下任一条件，则会进行记录：
 * 1. 使用 @ApiOperation + 非 @GetMapping
 * 2. 使用 @OperateLog 注解
 *
 * @author CareyQ
 */
@Slf4j
@Aspect
public class OperateLogAspect {

    @Around("@annotation(operation)")
    public Object around(ProceedingJoinPoint joinPoint, Operation operation) throws Throwable {
        OperateLog operateLog = getMethodAnnotation(joinPoint, OperateLog.class);
        return aroundHandle(joinPoint, operateLog, operation);
    }

    @Around("!@annotation(io.swagger.v3.oas.annotations.Operation) && @annotation(operateLog)")
    public Object around(ProceedingJoinPoint joinPoint, OperateLog operateLog) throws Throwable {
        return aroundHandle(joinPoint, operateLog, null);
    }

    private Object aroundHandle(ProceedingJoinPoint joinPoint, OperateLog operateLog, Operation operation) throws Throwable {
        Integer userType = WebUtils.getLoginUserType();
        if (!Objects.equals(userType, UserTypeEnum.ADMIN.getType())) {
            return joinPoint.proceed();
        }

        LocalDateTime startTime = LocalDateTime.now();
        try {

        } catch (Throwable ex) {

        } finally {

        }
        return null;
    }

    private void log(ProceedingJoinPoint joinPoint, OperateLog operateLog, Operation operation, LocalDateTime startTime, Object result, Throwable ex) {
        if (!enableLog(joinPoint, operateLog)) {
            return;
        }
        OperateLogRecord record = new OperateLogRecord();

    }

    /**
     * 判断是否开启日志记录
     *
     * @param joinPoint  切点
     * @param operateLog 注解
     * @return 结果
     */
    private static boolean enableLog(ProceedingJoinPoint joinPoint, OperateLog operateLog) {
        if (operateLog != null) {
            return operateLog.enable();
        }
        // RPC 请求必须添加注解
        String className = joinPoint.getSignature().getDeclaringType().getName();
        if (WebUtils.isRpcRequest(className)) {
            return false;
        }

        return obtainFirstLogRequestMethod(obtainRequestMethod(joinPoint)) != null;
    }


    /**
     * 过滤方法请求，只保留 POST、PUT、DELETE
     *
     * @param requestMethods 方法
     * @return 结果
     */
    private static RequestMethod obtainFirstLogRequestMethod(RequestMethod[] requestMethods) {
        if (ArrayUtil.isEmpty(requestMethods)) {
            return null;
        }
        return Arrays.stream(requestMethods).filter(requestMethod ->
                        requestMethod == RequestMethod.POST
                                || requestMethod == RequestMethod.PUT
                                || requestMethod == RequestMethod.DELETE)
                .findFirst().orElse(null);
    }

    /**
     * 获取切点处的方法
     *
     * @param joinPoint 切点
     * @return 方法
     */
    private static RequestMethod[] obtainRequestMethod(ProceedingJoinPoint joinPoint) {
        RequestMapping requestMapping = AnnotationUtils.getAnnotation(((MethodSignature) joinPoint.getSignature()).getMethod(), RequestMapping.class);
        return requestMapping != null ? requestMapping.method() : new RequestMethod[]{};
    }

    private static <T extends Annotation> T getMethodAnnotation(ProceedingJoinPoint joinPoint, Class<T> annotationClass) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod().getDeclaringClass().getAnnotation(annotationClass);
    }
}
