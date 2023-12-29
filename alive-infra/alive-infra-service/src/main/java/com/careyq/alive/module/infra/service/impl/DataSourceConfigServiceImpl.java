package com.careyq.alive.module.infra.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.module.infra.convert.DataSourceConfigConvert;
import com.careyq.alive.module.infra.dto.DataSourceConfigDTO;
import com.careyq.alive.module.infra.entity.DataSourceConfig;
import com.careyq.alive.module.infra.mapper.DataSourceConfigMapper;
import com.careyq.alive.module.infra.service.DataSourceConfigService;
import com.careyq.alive.mybatis.core.service.impl.ServiceImplX;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static com.careyq.alive.module.infra.constants.InfraResultCode.DB_CONFIG_NAME_DUPLICATE;
import static com.careyq.alive.module.infra.constants.InfraResultCode.DB_CONFIG_NOT_FOUND;

/**
 * 数据源配置服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class DataSourceConfigServiceImpl extends ServiceImplX<DataSourceConfigMapper, DataSourceConfig> implements DataSourceConfigService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveConfig(DataSourceConfigDTO dto) {
        if (dto.getId() != null) {
            this.checkConfigExists(dto.getId());
        }
        boolean exists = this.lambdaQueryX()
                .neIfPresent(DataSourceConfig::getId, dto.getId())
                .eq(DataSourceConfig::getName, dto.getName())
                .exists();
        if (exists) {
            throw new CustomException(DB_CONFIG_NAME_DUPLICATE);
        }

        DataSourceConfig dataSourceConfig = BeanUtil.copyProperties(dto, DataSourceConfig.class);
        this.saveOrUpdate(dataSourceConfig);
        return dataSourceConfig.getId();
    }

    @Override
    public List<DataSourceConfigDTO> getDataSourceConfigList() {
        return this.list().stream().map(DataSourceConfigConvert.INSTANCE::convert).toList();
    }

    @Override
    public DataSourceConfigDTO getDataSourceConfigDetail(Long id) {
        DataSourceConfig config = this.checkConfigExists(id);
        return DataSourceConfigConvert.INSTANCE.convert(config);
    }

    @Override
    public void delDataSourceConfig(Long id) {
        this.checkConfigExists(id);
        this.removeById(id);
    }

    @Override
    public Map<Long, String> getDataSourceConfigMap(List<Long> ids) {
        return CollUtils.convertMap(this.listByIds(ids), DataSourceConfig::getId, DataSourceConfig::getName);
    }

    /**
     * 校验数据源配置是否存在
     *
     * @param id 数据源配置 ID
     * @return 数据源配置
     */
    private DataSourceConfig checkConfigExists(Long id) {
        if (id == null) {
            return null;
        }
        DataSourceConfig config = this.getById(id);
        if (config == null) {
            throw new CustomException(DB_CONFIG_NOT_FOUND);
        }
        return config;
    }
}
