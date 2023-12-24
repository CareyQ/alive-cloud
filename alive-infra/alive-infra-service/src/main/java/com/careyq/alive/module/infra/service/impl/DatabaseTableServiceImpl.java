package com.careyq.alive.module.infra.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.careyq.alive.module.infra.dto.DataSourceConfigDTO;
import com.careyq.alive.module.infra.service.DataSourceConfigService;
import com.careyq.alive.module.infra.service.DatabaseTableService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * 数据库服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class DatabaseTableServiceImpl implements DatabaseTableService {

    private final DataSourceConfigService configService;

    @Override
    public List<TableInfo> getTableList(Long dataSourceConfigId, String name, String comment) {
        List<TableInfo> tables = this.getTableList(dataSourceConfigId, null);
        return tables.stream()
                .filter(e -> (StrUtil.isEmpty(name) || e.getName().contains(name)) && (StrUtil.isEmpty(comment) || e.getComment().contains(comment)))
                .toList();
    }

    private List<TableInfo> getTableList(Long dataSourceConfigId, String name) {
        DataSourceConfigDTO config = configService.getDataSourceConfigDetail(dataSourceConfigId);
        Assert.notNull(config, "数据源({})不存在！", dataSourceConfigId);

        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(config.getUrl(), config.getUsername(), config.getPassword()).build();
        StrategyConfig.Builder strategyConfig = new StrategyConfig.Builder();
        if (StrUtil.isNotEmpty(name)) {
            strategyConfig.addInclude(name);
        }

        GlobalConfig globalConfig = new GlobalConfig.Builder().dateType(DateType.TIME_PACK).build();
        ConfigBuilder builder = new ConfigBuilder(null, dataSourceConfig, strategyConfig.build(), null, globalConfig, null);
        List<TableInfo> tables = builder.getTableInfoList();
        tables.sort(Comparator.comparing(TableInfo::getName));
        return tables;
    }
}
