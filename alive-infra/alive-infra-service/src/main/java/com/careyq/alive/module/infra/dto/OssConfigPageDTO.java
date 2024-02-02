package com.careyq.alive.module.infra.dto;

import com.careyq.alive.core.domain.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

/**
 * 对象存储配置分页 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 对象存储配置分页 DTO")
public class OssConfigPageDTO extends PageDTO {

    @Schema(description = "配置名称")
    private String name;

    @Schema(description = "开始日期")
    private LocalDate startDate;

    @Schema(description = "结束日期")
    private LocalDate endDate;

}