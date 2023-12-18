package com.careyq.alive.module.system.service;

import com.careyq.alive.mybatis.core.service.IServiceX;
import com.careyq.alive.module.system.entity.DictType;
import com.careyq.alive.module.system.vo.DictTypeVO;

import java.util.List;

/**
 * 字典类型服务
 *
 * @author CareyQ
 */
public interface DictTypeService extends IServiceX<DictType> {

    /**
     * 保存字典类型
     *
     * @param req 字典类型
     * @return 字典类型 ID
     */
    Long saveDictType(DictTypeVO req);

    /**
     * 获取字典类型列表
     *
     * @return 字典类型列表
     */
    List<DictTypeVO> getDictTypeList();

    /**
     * 获取字典类型详情
     *
     * @param id 字段类型 ID
     * @return 字典类型
     */
    DictTypeVO getDictTypeDetail(Long id);

    /**
     * 删除字典类型
     *
     * @param id 字典类型 ID
     */
    void delDictType(Long id);
}
