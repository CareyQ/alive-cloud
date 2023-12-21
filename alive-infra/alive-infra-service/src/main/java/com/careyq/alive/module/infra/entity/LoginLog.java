package com.careyq.alive.module.infra.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.careyq.alive.core.domain.BaseEntity;
import com.careyq.alive.module.system.enums.LoginResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 登录日志
 *
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("infra_login_log")
public class LoginLog extends BaseEntity {

    /**
     * 登录类型 {@link com.careyq.alive.module.infra.enums.LoginLogTypeEnum}
     */
    private Integer type;

    /**
     * 链路追踪编号
     */
    private String traceId;

    /**
     * 用户名，万一以后可以变更
     */
    private String username;

    /**
     * 登录结果 {@link LoginResultEnum}
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
