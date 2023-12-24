package com.careyq.alive.module.infra.convert;

import com.careyq.alive.module.infra.dto.DataSourceConfigDTO;
import com.careyq.alive.module.infra.entity.DataSourceConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 数据源配置相关转换器
 *
 * @author CareyQ
 */
@Mapper
public interface DataSourceConfigConvert {

    DataSourceConfigConvert INSTANCE = Mappers.getMapper(DataSourceConfigConvert.class);

    /**
     * 数据源配置转换为 DTO
     *
     * @param dataSourceConfig 数据源配置
     * @return DTO
     */
    DataSourceConfigDTO convert(DataSourceConfig dataSourceConfig);

}
