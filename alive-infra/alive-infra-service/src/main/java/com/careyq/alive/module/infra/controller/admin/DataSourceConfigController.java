package com.careyq.alive.module.infra.controller.admin;

import com.careyq.alive.core.domain.Result;
import com.careyq.alive.module.infra.dto.DataSourceConfigDTO;
import com.careyq.alive.module.infra.service.DataSourceConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据源配置
 *
 * @author CareyQ
 */
@Tag(name = "管理后台 - 数据源配置")
@RestController
@AllArgsConstructor
@RequestMapping("/infra/data-source-config")
public class DataSourceConfigController {

    private final DataSourceConfigService configService;

    @PostMapping("/save")
    @Operation(summary = "保存数据源配置")
    public Result<Long> saveConfig(@Validated @RequestBody DataSourceConfigDTO dto) {
        return Result.ok(configService.saveConfig(dto));
    }

    @GetMapping("/list")
    @Operation(summary = "获取数据源配置列表")
    public Result<List<DataSourceConfigDTO>> getDataSourceConfigList() {
        return Result.ok(configService.getDataSourceConfigList());
    }

    @GetMapping("/detail")
    @Operation(summary = "获取数据源配置详情")
    public Result<DataSourceConfigDTO> getDataSourceConfigDetail(@RequestParam Long id) {
        DataSourceConfigDTO res = configService.getDataSourceConfigDetail(id);
        res.setPassword(null);
        return Result.ok(res);
    }

    @DeleteMapping("/del")
    @Operation(summary = "删除数据源配置")
    public Result<Boolean> delDataSourceConfig(@RequestParam Long id) {
        configService.delDataSourceConfig(id);
        return Result.ok(true);
    }

}
