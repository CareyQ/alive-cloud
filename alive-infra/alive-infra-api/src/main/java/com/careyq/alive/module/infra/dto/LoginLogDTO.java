package com.careyq.alive.module.infra.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 登录日志
 *
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
public class LoginLogDTO {

    /**
     * 登录类型
     */
    private Integer type;

    /**
     * 链路追踪编号
     */
    private String traceId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录结果
     */
    private Integer result;

    /**
     * 登录 IP
     */
    private String ip;

    /**
     * 登录信息
     */
    private String ipInfo;

    /**
     * 设备
     */
    private String device;

}
