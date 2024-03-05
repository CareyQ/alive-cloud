package com.careyq.alive.module.infra.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.core.constants.CacheNames;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.util.JsonUtils;
import com.careyq.alive.module.infra.convert.FileConvert;
import com.careyq.alive.module.infra.dto.OssConfigDTO;
import com.careyq.alive.module.infra.dto.OssConfigPageDTO;
import com.careyq.alive.module.infra.entity.OssConfig;
import com.careyq.alive.module.infra.mapper.OssConfigMapper;
import com.careyq.alive.module.infra.service.OssConfigService;
import com.careyq.alive.module.infra.vo.OssConfigPageVO;
import com.careyq.alive.module.infra.vo.OssConfigVO;
import com.careyq.alive.redis.util.CacheUtils;
import com.careyq.alive.redis.util.RedisUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.careyq.alive.module.infra.constants.InfraResultCode.OSS_CONFIG_NAME_IS_EXISTS;
import static com.careyq.alive.module.infra.constants.InfraResultCode.OSS_CONFIG_NOT_EXISTS;
import static com.careyq.alive.oss.constants.OssConstants.DEFAULT_CONFIG_ID;


/**
 * 对象存储配置 服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class OssConfigServiceImpl extends ServiceImpl<OssConfigMapper, OssConfig> implements OssConfigService {

    @Override
    public void init() {
        this.list().forEach(ossConfig -> {
            if (Boolean.TRUE.equals(ossConfig.getMaster())) {
                RedisUtils.setNotExpireObject(DEFAULT_CONFIG_ID, ossConfig.getId());
            }
            CacheUtils.put(CacheNames.OSS_CONFIG, ossConfig.getId(), JsonUtils.toJson(ossConfig));
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOssConfig(OssConfigDTO dto) {
        boolean exists = this.lambdaQueryX()
                .neIfPresent(OssConfig::getId, dto.getId())
                .eq(OssConfig::getName, dto.getName())
                .exists();
        if (exists) {
            throw new CustomException(OSS_CONFIG_NAME_IS_EXISTS);
        }
        if (dto.getId() != null) {
            this.checkDataExists(dto.getId());
        }
        // 如果设置为主配置，则将其他配置设置为非主配置
        if (Boolean.TRUE.equals(dto.getMaster())) {
            this.lambdaUpdateX()
                    .neIfPresent(OssConfig::getId, dto.getId())
                    .set(OssConfig::getMaster, false)
                    .update();
        }
        OssConfig ossConfig = BeanUtil.copyProperties(dto, OssConfig.class);
        this.saveOrUpdate(ossConfig);
        return ossConfig.getId();
    }

    @Override
    public IPage<OssConfigPageVO> getOssConfigPage(OssConfigPageDTO dto) {
        IPage<OssConfig> page = this.lambdaQueryX()
            .likeIfPresent(OssConfig::getName, dto.getName())
            .dateBetween(OssConfig::getCreateTime, dto.getStartDate(), dto.getEndDate())
            .orderByDesc(OssConfig::getId)
            .page(new Page<>(dto.getCurrent(), dto.getSize()));
        if (page.getRecords().isEmpty()) {
            return new Page<>();
        }
        return page.convert(FileConvert.INSTANCE::configConvert);
    }

    @Override
    public OssConfigVO getOssConfigDetail(Long id) {
        OssConfig data = this.checkDataExists(id);
        return BeanUtil.copyProperties(data, OssConfigVO.class);
    }

    @Override
    public void delOssConfig(Long id) {
        this.checkDataExists(id);
        this.removeById(id);
    }

    /**
     * 校验对象存储配置是否存在
     *
     * @param id 编号
     * @return 对象存储配置
     */
    private OssConfig checkDataExists(Long id) {
        if (id == null) {
            return null;
        }
        OssConfig data = this.getById(id);
        if (data == null) {
            throw new CustomException(OSS_CONFIG_NOT_EXISTS);
        }
        return data;
    }



}
