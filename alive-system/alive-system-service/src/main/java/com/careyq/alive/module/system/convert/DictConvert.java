package com.careyq.alive.module.system.convert;

import com.careyq.alive.module.system.entity.DictData;
import com.careyq.alive.module.system.entity.DictType;
import com.careyq.alive.module.system.vo.DictDataVO;
import com.careyq.alive.module.system.vo.DictTypeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 字典相关转换器
 *
 * @author CareyQ
 */
@Mapper
public interface DictConvert {

    DictConvert INSTANCE = Mappers.getMapper(DictConvert.class);

    /**
     * 字典数据转换为 VO
     *
     * @param dictData DictData
     * @return VO
     */
    DictDataVO convert(DictData dictData);

    /**
     * 字典类型转换为 VO
     *
     * @param dictType DictData
     * @return VO
     */
    DictTypeVO convert(DictType dictType);

}
