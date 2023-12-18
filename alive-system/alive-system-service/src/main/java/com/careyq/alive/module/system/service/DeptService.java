package com.careyq.alive.module.system.service;

import cn.hutool.core.lang.tree.Tree;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.mybatis.core.service.IServiceX;
import com.careyq.alive.module.system.dto.DeptDTO;
import com.careyq.alive.module.system.dto.DeptSearchDTO;
import com.careyq.alive.module.system.entity.Dept;
import com.careyq.alive.module.system.vo.DeptVO;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 部门服务
 *
 * @author CareyQ
 */
public interface DeptService extends IServiceX<Dept> {

    /**
     * 保存部门
     *
     * @param dto 部门
     * @return 部门 ID
     */
    Long saveDept(DeptDTO dto);

    /**
     * 获取部门列表
     *
     * @param dto 部门筛选项
     * @return 部门列表
     */
    List<Tree<Long>> getDeptList(DeptSearchDTO dto);

    /**
     * 获取部门详情
     *
     * @param id 部门 ID
     * @return 部门
     */
    DeptVO getDeptDetail(Long id);

    /**
     * 删除部门
     *
     * @param id 部门 ID
     */
    void delDept(Long id);

    /**
     * 获取简单部门列表
     *
     * @return 部门列表
     */
    List<Tree<Long>> getDeptSimpleList();

    /**
     * 根据 ID 获取部门列表
     *
     * @param ids 部门 ID
     * @return 列表
     */
    List<Dept> getDeptList(Collection<Long> ids);

    /**
     * 根据 ID 获取部门 map
     *
     * @param ids 部门 ID
     * @return 部门 map
     */
    default Map<Long, Dept> getDeptMap(Collection<Long> ids) {
        List<Dept> deptList = this.getDeptList(ids);
        return CollUtils.convertMap(deptList, Dept::getId);
    }
}
