package com.careyq.alive.operatelog.core.enums;

import com.careyq.alive.operatelog.core.annotations.OperateLog;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作日志操作类型
 *
 * @author CareyQ
 */
@Getter
@AllArgsConstructor
public enum OperateTypeEnum {

    /**
     * 查询
     * 绝大多数情况下，不会记录查询动作，因为过于大量显得没有意义。
     * 在有需要的时候，通过声明 {@link OperateLog} 注解来记录
     */
    QUERY(1, new String[]{"查询", "获取"}),
    /**
     * 新增
     */
    CREATE(2, new String[]{"新增", "添加"}),
    /**
     * 修改
     */
    UPDATE(3, new String[]{"更新", "修改"}),
    /**
     * 删除
     */
    DELETE(4, null),
    /**
     * 导出
     */
    EXPORT(5, null),
    /**
     * 导入
     */
    IMPORT(6, null),
    /**
     * 其他
     * 在无法归类时，可以选择使用其他。因为还有操作名可以进一步标识
     */
    OTHER(0, null);

    /**
     * 类型
     */
    private final Integer type;
    private final String[] prefix;
}
