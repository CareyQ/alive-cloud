package com.careyq.alive.core.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 分页请求基类
 *
 * @author CareyQ
 */
@Data
@Schema(description = "分页参数")
public class PageDTO {

    /**
     * 当前页
     */
    @NotNull(message = "当前页数不能为空")
    @Min(value = 1, message = "当前页数不能小于 1")
    private Long current;

    /**
     * 页面大小
     */
    @NotNull(message = "页面大小不能为空")
    @Max(value = 100, message = "页面大小不能大于 100")
    private Long size;
}
