package com.careyq.alive.module.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.mybatis.core.service.impl.ServiceImplX;
import com.careyq.alive.module.system.convert.DictConvert;
import com.careyq.alive.module.system.entity.DictData;
import com.careyq.alive.module.system.entity.DictType;
import com.careyq.alive.module.system.mapper.DictDataMapper;
import com.careyq.alive.module.system.mapper.DictTypeMapper;
import com.careyq.alive.module.system.service.DictTypeService;
import com.careyq.alive.module.system.vo.DictTypeVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.careyq.alive.module.system.constants.SystemResultCode.*;

/**
 * 字典类型服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class DictTypeServiceImpl extends ServiceImplX<DictTypeMapper, DictType> implements DictTypeService {

    private final DictDataMapper dictDataMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveDictType(DictTypeVO req) {
        if (req.getId() != null) {
            this.checkTypeExists(req.getId());
        }
        List<DictType> dictTypes = this.lambdaQueryX()
                .neIfPresent(DictType::getId, req.getId())
                .and(e -> e.eq(DictType::getName, req.getName()).or().eq(DictType::getType, req.getType()))
                .list();
        for (DictType dictType : dictTypes) {
            if (dictType.getName().equals(req.getName())) {
                throw new CustomException(DICT_TYPE_NAME_DUPLICATE);
            }
            if (dictType.getType().equals(req.getType())) {
                throw new CustomException(DICT_TYPE_TYPE_DUPLICATE);
            }
        }

        DictType dictType = BeanUtil.copyProperties(req, DictType.class);
        this.saveOrUpdate(dictType);
        return dictType.getId();
    }

    @Override
    public List<DictTypeVO> getDictTypeList() {
        return this.lambdaQuery()
                .orderByDesc(DictType::getStatus)
                .list().stream()
                .map(DictConvert.INSTANCE::convert)
                .toList();
    }

    @Override
    public DictTypeVO getDictTypeDetail(Long id) {
        DictType dictType = this.checkTypeExists(id);
        return DictConvert.INSTANCE.convert(dictType);
    }

    @Override
    public void delDictType(Long id) {
        DictType dictType = this.checkTypeExists(id);
        boolean exists = dictDataMapper.exists(new LambdaQueryWrapper<DictData>()
                .eq(DictData::getDictType, dictType.getType()));
        if (exists) {
            throw new CustomException(DICT_TYPE_EXISTS_CHILDREN);
        }
        this.removeById(id);
    }

    /**
     * 校验字典类型是否存在
     *
     * @param id 字典类型 ID
     * @return 字典类型
     */
    private DictType checkTypeExists(Long id) {
        if (id == null) {
            return null;
        }
        DictType dictType = this.getById(id);
        if (dictType == null) {
            throw new CustomException(DICT_TYPE_NOT_EXISTS);
        }
        return dictType;
    }
}
