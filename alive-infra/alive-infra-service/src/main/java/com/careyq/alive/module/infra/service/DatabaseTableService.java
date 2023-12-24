package com.careyq.alive.module.infra.service;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;

import java.util.List;

/**
 * 数据库服务
 *
 * @author CareyQ
 */
public interface DatabaseTableService {

    /**
     * 获取表列表
     *
     * @param dataSourceConfigId 数据源配置编号
     * @param name               表名称
     * @param comment            表描述
     * @return 表列表
     */
    List<TableInfo> getTableList(Long dataSourceConfigId, String name, String comment);

}
