package com.careyq.alive.module.system.controller.admin;

import com.careyq.alive.core.domain.Result;
import com.careyq.alive.module.system.service.DictTypeService;
import com.careyq.alive.module.system.vo.DictTypeVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典类型
 *
 * @author CareyQ
 */
@Tag(name = "管理后台 - 字典类型")
@RestController
@AllArgsConstructor
@RequestMapping("/system/dict-type")
public class DictTypeController {

    private final DictTypeService dictTypeService;

    @PostMapping("/save")
    @Operation(summary = "保存字典类型")
    public Result<Long> saveDictType(@Validated @RequestBody DictTypeVO req) {
        return Result.ok(dictTypeService.saveDictType(req));
    }

    @GetMapping("/list")
    @Operation(summary = "获取字典类型列表")
    public Result<List<DictTypeVO>> getDictTypeList() {
        return Result.ok(dictTypeService.getDictTypeList());
    }

    @GetMapping("/detail")
    @Operation(summary = "获取字典类型详情")
    public Result<DictTypeVO> getDictTypeDetail(@RequestParam Long id) {
        return Result.ok(dictTypeService.getDictTypeDetail(id));
    }

    @DeleteMapping("/del")
    @Operation(summary = "删除字典类型")
    public Result<Boolean> delDictType(@RequestParam Long id) {
        dictTypeService.delDictType(id);
        return Result.ok(true);
    }
}
