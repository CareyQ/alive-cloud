package com.careyq.alive.module.infra.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.careyq.alive.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 数据源配置
 *
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("infra_data_source_config")
public class DataSourceConfig extends BaseEntity {

    /**
     * 数据源名称
     */
    private String name;

    /**
     * 数据源地址
     */
    private String url;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
