package com.careyq.alive.operatelog.core.aop;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.careyq.alive.core.constants.ResultCodeConstants;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.core.enums.UserTypeEnum;
import com.careyq.alive.core.util.JsonUtils;
import com.careyq.alive.core.util.ServletUtils;
import com.careyq.alive.core.util.TraceUtils;
import com.careyq.alive.operatelog.core.annotations.OperateLog;
import com.careyq.alive.operatelog.core.enums.OperateTypeEnum;
import com.careyq.alive.operatelog.core.service.OperateLogFrameworkService;
import com.careyq.alive.satoken.AuthHelper;
import com.careyq.alive.system.dto.OperateLogDTO;
import com.careyq.alive.web.util.WebUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.IntStream;

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

    @Resource
    private OperateLogFrameworkService operateLogFrameworkService;

    @Around("@annotation(operation)")
    public Object around(ProceedingJoinPoint joinPoint, Operation operation) throws Throwable {
        OperateLog operateLog = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(OperateLog.class);
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
            Object result = joinPoint.proceed();
            this.log(joinPoint, operateLog, operation, startTime, result, null);
            return result;
        } catch (Throwable ex) {
            this.log(joinPoint, operateLog, operation, startTime, null, ex);
            throw ex;
        }
    }

    private void log(ProceedingJoinPoint joinPoint, OperateLog operateLog, Operation operation, LocalDateTime startTime, Object result, Throwable ex) {
        try {
            if (!enableLog(joinPoint, operateLog)) {
                return;
            }

            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Long userId = AuthHelper.getUserId();
            OperateLogDTO record = new OperateLogDTO();
            record.setTraceId(TraceUtils.getTraceId())
                    .setUserId(userId)
                    .setCreator(userId)
                    .setUpdater(userId)
                    .setUserType(WebUtils.getLoginUserType())
                    .setJavaMethod(signature.toString())
                    .setStartTime(startTime);
            // module 属性
            if (operateLog != null) {
                record.setModule(operateLog.module());
            }
            if (StrUtil.isEmpty(record.getModule())) {
                Tag tag = signature.getMethod().getDeclaringClass().getAnnotation(Tag.class);
                if (tag != null) {
                    if (StrUtil.isNotEmpty(tag.name())) {
                        record.setModule(tag.name());
                    } else if (ArrayUtil.isNotEmpty(tag.description())) {
                        record.setModule(tag.description());
                    }
                }
            }
            // name 属性
            if (operateLog != null) {
                record.setName(operateLog.name());
            }
            if (StrUtil.isEmpty(record.getName()) && operation != null) {
                record.setName(operation.summary());
            }
            // type 属性
            if (operateLog != null && ArrayUtil.isNotEmpty(operateLog.type())) {
                record.setType(operateLog.type()[0].getType());
            }
            if (record.getType() == null) {
                RequestMethod requestMethod = obtainFirstMatchRequestMethod(obtainRequestMethod(joinPoint));
                OperateTypeEnum operateLogType = convertOperateLogType(requestMethod);
                record.setType(operateLogType != null ? operateLogType.getType() : null);
            }

            HttpServletRequest request = ServletUtils.getRequest();
            if (request != null) {
                record.setRequestMethod(request.getMethod())
                        .setRequestUrl(request.getRequestURI())
                        .setIp(ServletUtils.getClientIp(request))
                        .setDevice(ServletUtils.getUserAgentInfo(request));
            }

            if (operateLog == null || operateLog.logArgs()) {
                record.setJavaMethodArgs(obtainMethodArgs(signature, joinPoint.getArgs()));
            }
            if (operateLog == null || operateLog.logResultData()) {
                record.setResultData(obtainResultData(result));
            }
            record.setDuration((int) (LocalDateTimeUtil.between(startTime, LocalDateTime.now()).toMillis()));
            // （正常）处理 resultCode 和 resultMsg 字段
            if (result instanceof Result<?> commonResult) {
                record.setResultCode(commonResult.getCode());
                record.setResultMsg(commonResult.getMsg());
            } else {
                record.setResultCode(ResultCodeConstants.OK.code());
            }
            // （异常）处理 resultCode 和 resultMsg 字段
            if (ex != null) {
                record.setResultCode(ResultCodeConstants.SERVER_ERROR.code());
                record.setResultMsg(ExceptionUtil.getRootCauseMessage(ex));
            }

            operateLogFrameworkService.createOperateLog(record);
        } catch (Throwable e) {
            log.error("[operate-log][point({}) operateLog({}) apiOperation({}) result({}) exception({})]", joinPoint, operateLog, operation, result, ex, e);
        }
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

    private static RequestMethod obtainFirstMatchRequestMethod(RequestMethod[] requestMethods) {
        if (ArrayUtil.isEmpty(requestMethods)) {
            return null;
        }
        // 优先，匹配最优的 POST、PUT、DELETE
        RequestMethod result = obtainFirstLogRequestMethod(requestMethods);
        if (result != null) {
            return result;
        }
        // 然后，匹配次优的 GET
        result = Arrays.stream(requestMethods).filter(requestMethod -> requestMethod == RequestMethod.GET)
                .findFirst().orElse(null);
        if (result != null) {
            return result;
        }
        // 兜底，获得第一个
        return requestMethods[0];
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

    private static String obtainMethodArgs(MethodSignature methodSignature, Object[] argValues) {
        // TODO 提升：参数脱敏和忽略
        String[] argNames = methodSignature.getParameterNames();
        // 拼接参数
        Map<String, Object> args = MapUtil.newHashMap(argValues.length);
        for (int i = 0; i < argNames.length; i++) {
            String argName = argNames[i];
            Object argValue = argValues[i];
            // 被忽略时，标记为 ignore 字符串，避免和 null 混在一起
            args.put(argName, !isIgnoreArgs(argValue) ? argValue : "[ignore]");
        }
        return JsonUtils.toJsonString(args);
    }

    private static String obtainResultData(Object result) {
        // TODO 提升：结果脱敏和忽略
        if (result instanceof Result<?>) {
            result = ((Result<?>) result).getData();
        }
        return JsonUtils.toJsonString(result);
    }

    private static OperateTypeEnum convertOperateLogType(RequestMethod requestMethod) {
        if (requestMethod == null) {
            return null;
        }
        return switch (requestMethod) {
            case GET -> OperateTypeEnum.QUERY;
            case POST -> OperateTypeEnum.CREATE;
            case PUT -> OperateTypeEnum.UPDATE;
            case DELETE -> OperateTypeEnum.DELETE;
            default -> OperateTypeEnum.OTHER;
        };
    }

    private static boolean isIgnoreArgs(Object object) {
        Class<?> clazz = object.getClass();
        // 处理数组的情况
        if (clazz.isArray()) {
            return IntStream.range(0, Array.getLength(object))
                    .anyMatch(index -> isIgnoreArgs(Array.get(object, index)));
        }
        // 递归，处理数组、Collection、Map 的情况
        if (Collection.class.isAssignableFrom(clazz)) {
            return ((Collection<?>) object).stream()
                    .anyMatch((Predicate<Object>) OperateLogAspect::isIgnoreArgs);
        }
        if (Map.class.isAssignableFrom(clazz)) {
            return isIgnoreArgs(((Map<?, ?>) object).values());
        }
        // obj
        return object instanceof MultipartFile
                || object instanceof HttpServletRequest
                || object instanceof HttpServletResponse
                || object instanceof BindingResult;
    }

}
