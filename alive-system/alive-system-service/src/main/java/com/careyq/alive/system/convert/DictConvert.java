package com.careyq.alive.system.convert;

import com.careyq.alive.system.entity.DictData;
import com.careyq.alive.system.entity.DictType;
import com.careyq.alive.system.vo.DictDataVO;
import com.careyq.alive.system.vo.DictTypeVO;
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

    DictDataVO convert(DictData dictData);

    DictTypeVO convert(DictType dictType);

}
