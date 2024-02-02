package com.careyq.alive.module.infra.convert;

import com.careyq.alive.module.infra.entity.File;
import com.careyq.alive.module.infra.entity.OssConfig;
import com.careyq.alive.module.infra.vo.FilePageVO;
import com.careyq.alive.module.infra.vo.OssConfigPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

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
    FilePageVO fileConvert(File file);
}
