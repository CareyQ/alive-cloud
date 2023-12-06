package com.careyq.alive.system.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.system.dto.DictDataPageDTO;
import com.careyq.alive.system.service.DictDataService;
import com.careyq.alive.system.vo.DictDataVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 字典数据
 *
 * @author CareyQ
 */
@Tag(name = "管理后台 - 字典数据")
@RestController
@AllArgsConstructor
@RequestMapping("/dict-data")
public class DictDataController {

    private final DictDataService dictDataService;

    @PostMapping("/save")
    @Operation(summary = "保存字典数据")
    public Result<Long> saveDictData(@Validated @RequestBody DictDataVO req) {
        return Result.ok(dictDataService.saveDictData(req));
    }

    @PostMapping("/page")
    @Operation(summary = "获取字典数据分页")
    public Result<IPage<DictDataVO>> getDictDataPage(@Validated @RequestBody DictDataPageDTO dto) {
        return Result.ok(dictDataService.getDictDataPage(dto));
    }

    @GetMapping("/detail")
    @Operation(summary = "获取字典数据详情")
    public Result<DictDataVO> getDictDataDetail(@RequestParam Long id) {
        return Result.ok(dictDataService.getDictDataDetail(id));
    }

    @DeleteMapping("/del")
    @Operation(summary = "删除字典数据")
    public Result<Boolean> delDictData(@RequestParam Long id) {
        dictDataService.delDictData(id);
        return Result.ok(true);
    }

    @GetMapping("/list")
    @Operation(summary = "获取字典数据，根据类型分组，只有启用状态")
    public Result<Map<String, List<DictDataVO>>> getDictDataMap() {
        return Result.ok(dictDataService.getDictDataMap());
    }
}
