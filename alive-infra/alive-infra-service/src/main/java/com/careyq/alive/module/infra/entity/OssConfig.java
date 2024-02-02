package com.careyq.alive.module.infra.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.careyq.alive.core.domain.BaseEntity;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 对象存储配置
 *
 * @author CareyQ
 */
@Data
@TableName("infra_oss_config")
public class OssConfig extends BaseEntity {

    /**
     * 配置名称 
     */
    private String name;

    /**
     * accessKey 
     */
    private String accessKey;

    /**
     * 秘钥 
     */
    private String secretKey;

    /**
     * 桶名称 
     */
    private String bucket;

    /**
     * 访问站点 
     */
    private String endpoint;

    /**
     * 域 
     */
    private String region;

    /**
     * 是否为主配置 
     */
    private Boolean master;


}