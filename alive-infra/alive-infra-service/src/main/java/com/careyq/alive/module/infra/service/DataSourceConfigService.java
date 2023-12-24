package com.careyq.alive.module.infra.service;

import com.careyq.alive.module.infra.dto.DataSourceConfigDTO;
import com.careyq.alive.module.infra.entity.DataSourceConfig;
import com.careyq.alive.mybatis.core.service.IServiceX;

import java.util.List;

/**
 * 日数据源配置服务
 *
 * @author CareyQ
 */
public interface DataSourceConfigService extends IServiceX<DataSourceConfig> {

    /**
     * 保存数据源配置
     *
     * @param dto 配置
     * @return 配置 ID
     */
    Long saveConfig(DataSourceConfigDTO dto);

    /**
     * 获取数据源配置列表
     *
     * @return 数据源配置列表
     */
    List<DataSourceConfigDTO> getDataSourceConfigList();

    /**
     * 获取数据源配置详情
     *
     * @param id 配置编号
     * @return 数据源配置详情
     */
    DataSourceConfigDTO getDataSourceConfigDetail(Long id);

    /**
     * 删除数据源配置
     *
     * @param id 操作日志
     */
    void delDataSourceConfig(Long id);

}
