package com.careyq.alive.module.infra.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.module.infra.convert.FileConvert;
import com.careyq.alive.module.infra.dto.FileDTO;
import com.careyq.alive.module.infra.dto.FilePageDTO;
import com.careyq.alive.module.infra.entity.File;
import com.careyq.alive.module.infra.mapper.FileMapper;
import com.careyq.alive.module.infra.service.FileService;
import com.careyq.alive.module.infra.vo.FilePageVO;
import com.careyq.alive.module.infra.vo.FileVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文件 服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    private final FileMapper fileMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveFile(FileDTO dto) {
        boolean exists = this.lambdaQueryX()
                .neIfPresent(File::getId, dto.getId())
                // TODO
                .exists();
        if (exists) {
            throw new CustomException();
        }
        if (dto.getId() != null) {
            this.checkDataExists(dto.getId());
        }
        File file = BeanUtil.copyProperties(dto, File.class);
        this.saveOrUpdate(file);
        return file.getId();
    }

    @Override
    public IPage<FilePageVO> getFilePage(FilePageDTO dto) {
        IPage<File> page = this.lambdaQueryX()
            .eqIfPresent(File::getConfigId, dto.getConfigId())
            .likeIfPresent(File::getName, dto.getName())
            .eqIfPresent(File::getPath, dto.getPath())
            .orderByDesc(File::getId)
            .page(new Page<>(dto.getCurrent(), dto.getSize()));
        if (page.getRecords().isEmpty()) {
            return new Page<>();
        }
        return page.convert(FileConvert.INSTANCE::fileConvert);
    }

    @Override
    public FileVO getFileDetail(Long id) {
        File data = this.checkDataExists(id);
        return BeanUtil.copyProperties(data, FileVO.class);
    }

    @Override
    public void delFile(Long id) {
        this.checkDataExists(id);
        this.removeById(id);
    }

    /**
     * 校验文件是否存在
     *
     * @param id 编号
     * @return 文件
     */
    private File checkDataExists(Long id) {
        if (id == null) {
            return null;
        }
        File data = this.getById(id);
        if (data == null) {
            throw new CustomException();
        }
        return data;
    }

}
