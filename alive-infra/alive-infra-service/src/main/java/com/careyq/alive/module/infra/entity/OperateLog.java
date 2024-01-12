package com.careyq.alive.module.infra.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.careyq.alive.core.domain.BaseEntity;
import com.careyq.alive.core.domain.ResultCode;
import com.careyq.alive.operatelog.core.enums.OperateTypeEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 操作日志
 *
 * @author CareyQ
 */
@Data
@TableName("infra_operate_log")
public class OperateLog extends BaseEntity {

    /**
     * {@link #javaMethodArgs} 最大长度
     */
    public static final Integer JAVA_METHOD_ARGS_MAX_LENGTH = 8000;

    /**
     * {@link #resultData} 最大长度
     */
    public static final Integer RESULT_MAX_LENGTH = 4000;

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
     * 操作模块
     */
    private String module;

    /**
     * 操作名
     */
    private String name;

    /**
     * 操作分类 {@link OperateTypeEnum}
     */
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
    private String requestMethod;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * IP
     */
    private String ip;

    /**
     * 设备
     */
    private String device;

    /**
     * Java 方法名
     */
    private String javaMethod;

    /**
     * Java 方法的参数
     */
    private String javaMethodArgs;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 执行时长，单位：毫秒
     */
    private Integer duration;

    /**
     * 结果码，{@link ResultCode#code()}
     */
    private Integer resultCode;

    /**
     * 结果提示，{@link ResultCode#msg()}
     */
    private String resultMsg;

    /**
     * 结果数据
     */
    private String resultData;

}
