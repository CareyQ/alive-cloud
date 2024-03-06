package com.careyq.alive.module.infra.convert;

import com.careyq.alive.module.infra.entity.File;
import com.careyq.alive.module.infra.entity.OssConfig;
import com.careyq.alive.module.infra.vo.FilePageVO;
import com.careyq.alive.module.infra.vo.OssConfigPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Map;

/**
 * 文件相关转换器
 *
 * @author CareyQ
 */
@Mapper
public interface FileConvert {

    FileConvert INSTANCE = Mappers.getMapper(FileConvert.class);

    /**
     * 存储配置转换为分页 VO
     *
     * @param config OssConfig
     * @return VO
     */
    OssConfigPageVO configConvert(OssConfig config);

    /**
     * 文件转换为分页 VO
     *
     * @param file File
     * @return VO
     */
    @Mapping(target = "configName", ignore = true)
    FilePageVO fileConvert(File file);

    /**
     * 文件转换为分页 VO
     *
     * @param file      File
     * @param configMap 存储配置 map
     * @return VO
     */
    default FilePageVO fileConvert(File file, Map<Long, String> configMap) {
        FilePageVO vo = fileConvert(file);
        vo.setConfigName(configMap.get(file.getConfigId()));
        return vo;
    }
}
