package com.careyq.alive.module.infra.dto;

import com.careyq.alive.core.domain.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * 登录日志分页 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 登录日志分页 DTO")
public class LoginLogPageDTO extends PageDTO {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "IP")
    private String ip;

    @Schema(description = "开始日期")
    private LocalDate startDate;

    @Schema(description = "结束日期")
    private LocalDate endDate;
}
