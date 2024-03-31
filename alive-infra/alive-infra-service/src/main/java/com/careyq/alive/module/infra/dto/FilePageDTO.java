package com.careyq.alive.module.infra.dto;

import com.careyq.alive.core.domain.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * 文件分页 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 文件分页 DTO")
public class FilePageDTO extends PageDTO {

    @Schema(description = "文件名")
    private String name;

    @Schema(description = "文件路径")
    private String path;

    @Schema(description = "开始日期")
    private LocalDate startDate;

    @Schema(description = "结束日期")
    private LocalDate endDate;

}