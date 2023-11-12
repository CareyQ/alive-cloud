package com.careyq.alive.core.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * 分页请求基类
 *
 * @author CareyQ
 * @since 2023-11-11
 */
@Data
@Schema(description = "分页参数")
public class PageDTO {

    /**
     * 默认当前页码
     */
    private static final long CURRENT = 1;
    /**
     * 默认页面条数
     */
    private static final long SIZE = 10;

    /**
     * 当前页
     */
    @Min(value = 1, message = "当前页数不能小于 1")
    private Long current = CURRENT;

    /**
     * 页面大小
     */
    @Max(value = 100, message = "页面大小不能大于 100")
    private Long size = SIZE;
}
