package com.careyq.alive.module.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.alive.module.infra.entity.DataSourceConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据源配置 mapper
 *
 * @author CareyQ
 */
@Mapper
public interface DataSourceConfigMapper extends BaseMapper<DataSourceConfig> {
}
