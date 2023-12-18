package com.careyq.alive.module.infra.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.careyq.alive.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 异常错误日志
 *
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("infra_error_log")
public class ErrorLog extends BaseEntity {

    /**
     * 链路追踪编号
     */
    private String traceId;

    /**
     * 用户编号
     */
    private Long userId;

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

    /**
     * 处理状态
     */
    private Integer processStatus;

    /**
     * 处理时间
     */
    private LocalDateTime processTime;

    /**
     * 处理人的用户 ID
     */
    private Long processUserId;

}
