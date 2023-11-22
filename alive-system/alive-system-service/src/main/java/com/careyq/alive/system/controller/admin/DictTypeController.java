package com.careyq.alive.system.controller.admin;

import com.careyq.alive.core.domain.EntryVO;
import com.careyq.alive.core.domain.R;
import com.careyq.alive.system.service.DictTypeService;
import com.careyq.alive.system.vo.DictTypeVO;
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
@RequestMapping("/dict-type")
public class DictTypeController {

    private final DictTypeService dictTypeService;

    @PostMapping("/save")
    @Operation(summary = "保存字典类型")
    public R<Long> saveDictType(@Validated @RequestBody DictTypeVO req) {
        return R.ok(dictTypeService.saveDictType(req));
    }

    @GetMapping("/list")
    @Operation(summary = "获取字典类型列表")
    public R<List<EntryVO>> getDictTypeList() {
        return R.ok(dictTypeService.getDictTypeList());
    }

    @GetMapping("/detail")
    @Operation(summary = "获取字典类型详情")
    public R<DictTypeVO> getDictTypeDetail(@RequestParam Long id) {
        return R.ok(dictTypeService.getDictTypeDetail(id));
    }


    @DeleteMapping("/del")
    @Operation(summary = "删除字典类型")
    public R<Boolean> delDictType(@RequestParam Long id) {
        dictTypeService.delDictType(id);
        return R.ok(true);
    }
}
