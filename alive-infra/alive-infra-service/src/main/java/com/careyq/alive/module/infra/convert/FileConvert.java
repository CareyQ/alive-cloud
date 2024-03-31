package com.careyq.alive.module.infra.convert;

import com.careyq.alive.module.infra.entity.File;
import com.careyq.alive.module.infra.vo.FilePageVO;
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
     * 文件转换为分页 VO
     *
     * @param file File
     * @return VO
     */
    FilePageVO fileConvert(File file);
}
