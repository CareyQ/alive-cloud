package com.careyq.alive.module.infra.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 操作日志 DTO
 *
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
public class OperateLogDTO {

    /**
     * 链路追踪编号
     */
    private String traceId;

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    /**
     * 用户类型
     */
    @NotNull(message = "用户类型不能为空")
    private Integer userType;

    /**
     * 操作模块
     */
    @NotBlank(message = "操作模块不能为空")
    private String module;

    /**
     * 操作名
     */
    @NotBlank(message = "操作名")
    private String name;

    /**
     * 操作分类
     */
    @NotNull(message = "操作分类不能为空")
    private Integer type;

    /**
     * 操作明细
     */
    private String content;

    /**
     * 拓展字段
     */
    private Map<String, Object> extra;

    /**
     * 请求方法名
     */
    @NotBlank(message = "请求方法名不能为空")
    private String requestMethod;

    /**
     * 请求地址
     */
    @NotBlank(message = "请求地址不能为空")
    private String requestUrl;

    /**
     * IP
     */
    @NotBlank(message = "IP 不能为空")
    private String ip;

    /**
     * 设备信息
     */
    @NotBlank(message = "设备信息不能为空")
    private String device;

    /**
     * Java 方法名
     */
    @NotBlank(message = "Java 方法名不能为空")
    private String javaMethod;

    /**
     * Java 方法参数
     */
    private String javaMethodArgs;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空")
    private LocalDateTime startTime;

    /**
     * 执行时长，单位：毫秒
     */
    @NotNull(message = "执行时长不能为空")
    private Integer duration;

    /**
     * 结果码
     */
    @NotNull(message = "结果码不能为空")
    private Integer resultCode;

    /**
     * 结果提示
     */
    private String resultMsg;

    /**
     * 结果数据
     */
    private String resultData;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 更新人
     */
    private Long updater;
}
