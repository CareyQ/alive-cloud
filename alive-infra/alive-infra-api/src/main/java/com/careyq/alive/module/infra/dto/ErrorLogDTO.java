package com.careyq.alive.module.infra.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 异常错误日志
 *
 * @author CareyQ
 */
@Data
public class ErrorLogDTO {

    /**
     * 链路追踪编号
     */
    private String traceId;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户类型 {@link com.careyq.alive.core.enums.UserTypeEnum}
     */
    private Integer userType;

    /**
     * 请求方法名
     */
    private String requestMethod;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 请求 curl
     */
    private String requestCurl;

    /**
     * IP 地址
     */
    private String ip;

    /**
     * 设备名
     */
    private String device;

    /**
     * 异常类全名，例如：java.lang.NullPointerException
     */
    private String exName;

    /**
     * 异常消息
     */
    private String exMessage;

    /**
     * 异常导致的根消息
     */
    private String exRootCauseMessage;

    /**
     * 异常的栈轨迹
     */
    private String exStackTrace;

}
