package com.careyq.alive.module.infra.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.module.infra.dto.OssConfigDTO;
import com.careyq.alive.module.infra.dto.OssConfigPageDTO;
import com.careyq.alive.module.infra.service.OssConfigService;
import com.careyq.alive.module.infra.vo.OssConfigPageVO;
import com.careyq.alive.module.infra.vo.OssConfigVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 对象存储配置
 *
 * @author CareyQ
 */
@RestController
@AllArgsConstructor
@RequestMapping("/infra/oss-config")
@Tag(name = "管理后台 - 对象存储配置")
public class OssConfigController {

    private final OssConfigService ossConfigService;

    @PostMapping("/save")
    @Operation(summary = "保存对象存储配置")
    public Result<Long> saveOssConfig(@Validated @RequestBody OssConfigDTO dto) {
        return Result.ok(ossConfigService.saveOssConfig(dto));
    }

    @PostMapping("/page")
    @Operation(summary = "获取对象存储配置分页")
    public Result<IPage<OssConfigPageVO>> getOssConfigPage(@Validated @RequestBody OssConfigPageDTO dto) {
        return Result.ok(ossConfigService.getOssConfigPage(dto));
    }

    @GetMapping("/detail")
    @Operation(summary = "获取对象存储配置详情")
    public Result<OssConfigVO> getOssConfigDetail(@RequestParam Long id) {
        return Result.ok(ossConfigService.getOssConfigDetail(id));
    }

    @DeleteMapping("/del")
    @Operation(summary = "删除对象存储配置")
    public Result<Boolean> delOssConfig(@RequestParam Long id) {
        ossConfigService.delOssConfig(id);
        return Result.ok(true);
    }
}
