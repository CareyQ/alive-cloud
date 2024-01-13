package com.careyq.alive.module.infra.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.module.infra.dto.CodegenImportDTO;
import com.careyq.alive.module.infra.dto.CodegenTablePageDTO;
import com.careyq.alive.module.infra.vo.CodegenDetailVO;
import com.careyq.alive.module.infra.vo.CodegenTablePageVO;
import com.careyq.alive.module.infra.vo.DbTableVO;

import java.util.List;
import java.util.Map;

/**
 * 代码生成服务
 *
 * @author CareyQ
 */
public interface CodegenService {

    /**
     * 获取数据库表列表
     *
     * @param dataSourceConfigId 数据源配置编号
     * @param name               表名称
     * @param comment            表描述
     * @return 表列表
     */
    List<DbTableVO> getDbTableList(Long dataSourceConfigId, String name, String comment);

    /**
     * 获取表定义分页
     *
     * @param dto 分页参数
     * @return 表定义分页
     */
    IPage<CodegenTablePageVO> getCodegenTablePage(CodegenTablePageDTO dto);

    /**
     * 导入数据库表结构
     *
     * @param dto 导入参数
     * @return 表编号列表
     */
    List<Long> importCodegenTable(CodegenImportDTO dto);

    /**
     * 获取代码生成表详情
     *
     * @param tableId 表编号
     * @return 表详情
     */
    CodegenDetailVO getCodegenDetail(Long tableId);

    /**
     * 生成代码
     *
     * @param tableId 表编号
     * @return 代码
     */
    Map<String, String> generationCode(Long tableId);
}
