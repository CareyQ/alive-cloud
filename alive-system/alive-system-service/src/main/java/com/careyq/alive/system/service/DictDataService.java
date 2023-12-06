package com.careyq.alive.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.mybatis.core.service.IServiceX;
import com.careyq.alive.system.dto.DictDataPageDTO;
import com.careyq.alive.system.entity.DictData;
import com.careyq.alive.system.vo.DictDataVO;

import java.util.List;
import java.util.Map;

/**
 * 字典数据服务
 *
 * @author CareyQ
 */
public interface DictDataService extends IServiceX<DictData> {

    /**
     * 保存字典数据
     *
     * @param req 字典数据
     * @return 字典数据 ID
     */
    Long saveDictData(DictDataVO req);

    /**
     * 获取字典数据分页
     *
     * @return 字典数据分页
     */
    IPage<DictDataVO> getDictDataPage(DictDataPageDTO dto);

    /**
     * 获取字典数据详情
     *
     * @param id 字典数据 ID
     * @return 字典数据
     */
    DictDataVO getDictDataDetail(Long id);

    /**
     * 删除字典数据
     *
     * @param id 字典数据 ID
     */
    void delDictData(Long id);

    /**
     * 获取字典数据，根据字典类型分组
     *
     * @return 字典数据
     */
    Map<String, List<DictDataVO>> getDictDataMap();
}
