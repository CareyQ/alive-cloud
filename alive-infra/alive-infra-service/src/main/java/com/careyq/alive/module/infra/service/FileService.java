package com.careyq.alive.module.infra.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.module.infra.dto.FilePageDTO;
import com.careyq.alive.module.infra.entity.File;
import com.careyq.alive.module.infra.vo.FilePageVO;
import com.careyq.alive.module.infra.vo.FileVO;
import com.careyq.alive.mybatis.core.service.IServiceX;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 文件 服务
 *
 * @author CareyQ
 */
public interface FileService extends IServiceX<File> {

    /**
     * 获取文件目录
     *
     * @return 目录列表
     */
    List<String> getFileFolder();

    /**
     * 上传文件
     *
     * @param file   文件
     * @param folder 文件夹
     * @return 地址
     * @throws IOException IO 异常
     */
    String uploadFile(MultipartFile file, String folder) throws IOException;

    /**
     * 获取文件分页
     *
     * @param dto 分页筛选项
     * @return 分页
     */
    IPage<FilePageVO> getFilePage(FilePageDTO dto);

    /**
     * 获取文件详情
     *
     * @param id 编号
     * @return 文件
     */
    FileVO getFileDetail(Long id);

    /**
     * 删除文件
     *
     * @param id 编号
     */
    void delFile(Long id);
}
