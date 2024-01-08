package com.careyq.alive.module.infra.convert;

import com.careyq.alive.module.infra.entity.CodegenColumn;
import com.careyq.alive.module.infra.entity.CodegenTable;
import com.careyq.alive.module.infra.vo.CodegenColumnVO;
import com.careyq.alive.module.infra.vo.CodegenDetailVO;
import com.careyq.alive.module.infra.vo.CodegenTablePageVO;
import com.careyq.alive.module.infra.vo.CodegenTableVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 代码生成相关转换器
 *
 * @author CareyQ
 */
@Mapper
public interface CodegenConvert {

    CodegenConvert INSTANCE = Mappers.getMapper(CodegenConvert.class);

    /**
     * 表定义转换为分页 VO
     *
     * @param codegenTable   表定义
     * @param dataSourceName 数据源名称
     * @return DTO
     */
    CodegenTablePageVO convert(CodegenTable codegenTable, String dataSourceName);

    /**
     * 转换为 VO
     *
     * @param codegenTable CodegenTable
     * @return VO
     */
    CodegenTableVO convert(CodegenTable codegenTable);

    /**
     * 转换为 VO
     *
     * @param list CodegenColumn
     * @return VO
     */
    List<CodegenColumnVO> convertList(List<CodegenColumn> list);

    /**
     * 转换为表详情
     *
     * @param table   表定义
     * @param columns 表字段
     * @return 表详情
     */
    default CodegenDetailVO convert(CodegenTable table, List<CodegenColumn> columns) {
        CodegenDetailVO res = new CodegenDetailVO();
        res.setTable(CodegenConvert.INSTANCE.convert(table));
        res.setColumns(CodegenConvert.INSTANCE.convertList(columns));
        return res;
    }
}
