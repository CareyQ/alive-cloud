package com.careyq.alive.system.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.alive.system.dto.DeptDTO;
import com.careyq.alive.system.dto.DeptSearchDTO;
import com.careyq.alive.system.entity.Dept;
import com.careyq.alive.system.vo.DeptVO;

import java.util.List;

/**
 * 部门服务
 *
 * @author CareyQ
 */
public interface DeptService extends IService<Dept> {

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
}
