package com.careyq.alive.module.infra.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.exception.FileUploadException;
import com.careyq.alive.module.infra.convert.FileConvert;
import com.careyq.alive.module.infra.dto.FilePageDTO;
import com.careyq.alive.module.infra.entity.File;
import com.careyq.alive.module.infra.mapper.FileMapper;
import com.careyq.alive.module.infra.service.FileService;
import com.careyq.alive.module.infra.vo.FilePageVO;
import com.careyq.alive.module.infra.vo.FileVO;
import com.careyq.alive.oss.MinioUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件 服务实现
 *
 * @author CareyQ
 */
@Slf4j
@Service
@AllArgsConstructor
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    private static final ThreadLocal<Tika> TIKA = TransmittableThreadLocal.withInitial(Tika::new);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String uploadFile(MultipartFile file, String folder) {
        String url;
        try {
            String filePath = MinioUtils.upload(file, folder);
            url = MinioUtils.getFileUrl(filePath);

            byte[] content = IoUtil.readBytes(file.getInputStream());
            String type = getFileType(content, filePath);

            File saveFile = new File();
            saveFile.setName(file.getOriginalFilename())
                    .setFolder(folder)
                    .setPath(filePath)
                    .setType(type)
                    .setSize(content.length);
            this.save(saveFile);
        } catch (Exception ex) {
            log.error("文件上传出错", ex);
            throw new FileUploadException(ex.getMessage());
        }
        return url;
    }

    /**
     * 获取文件类型
     *
     * @param content 文件数据内容
     * @param name    文件名
     * @return 文件类型
     */
    private static String getFileType(byte[] content, String name) {
        String type = TIKA.get().detect(content, name);
        TIKA.remove();
        return type;
    }

    @Override
    public IPage<FilePageVO> getFilePage(FilePageDTO dto) {
        IPage<File> page = this.lambdaQueryX()
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
