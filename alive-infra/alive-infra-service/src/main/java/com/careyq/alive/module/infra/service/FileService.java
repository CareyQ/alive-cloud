package com.careyq.alive.module.infra.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.mybatis.core.service.IServiceX;
import com.careyq.alive.module.infra.entity.File;
import com.careyq.alive.module.infra.dto.*;
import com.careyq.alive.module.infra.vo.*;

/**
 * 文件 服务
 *
 * @author CareyQ
 */
public interface FileService extends IServiceX<File> {

    /**
     * 保存文件
     *
     * @param dto 文件
     * @return 编号
     */
    Long saveFile(FileDTO dto);

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
