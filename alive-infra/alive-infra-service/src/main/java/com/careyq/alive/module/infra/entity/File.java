package com.careyq.alive.module.infra.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.careyq.alive.core.domain.BaseEntity;
import lombok.Data;

/**
 * 文件
 *
 * @author CareyQ
 */
@Data
@TableName("infra_file")
public class File extends BaseEntity {

    /**
     * 文件名
     */
    private String name;

    /**
     * 目录
     */
    private String folder;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件大小
     */
    private Integer size;


}