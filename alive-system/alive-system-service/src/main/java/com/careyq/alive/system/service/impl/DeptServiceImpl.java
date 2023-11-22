package com.careyq.alive.system.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.core.enums.CommonStatusEnum;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.system.dto.DeptDTO;
import com.careyq.alive.system.entity.Dept;
import com.careyq.alive.system.mapper.DeptMapper;
import com.careyq.alive.system.service.DeptService;
import com.careyq.alive.system.vo.DeptVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.careyq.alive.system.constants.SystemResultCode.*;

/**
 * 部门服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Override
    public Long saveDept(DeptDTO dto) {
        if (dto.getId() != null) {
            this.checkDeptExists(dto.getId());
        }

        return null;
    }

    @Override
    public List<Tree<Long>> getDeptList() {
        return null;
    }

    @Override
    public DeptVO getDeptDetail(Long id) {
        return null;
    }

    @Override
    public void delDept(Long id) {

    }

    @Override
    public List<Tree<Long>> getDeptSimpleList() {
        List<Dept> deptList = this.lambdaQuery()
                .eq(Dept::getStatus, CommonStatusEnum.ENABLE.getStatus())
                .orderByAsc(Dept::getSort)
                .list();
        return TreeUtil.build(deptList, Dept.ROOT_ID, (node, tree) -> tree.setId(node.getId())
                .setParentId(node.getParentId())
                .setName(node.getName()));
    }

    /**
     * 校验父级部门
     *
     * @param dept 部门
     * @return 部门
     */
    private void checkDeptParent(Dept dept) {
        if (dept.getParentId() == null) {
            return;
        }
        if (dept.getParentId().equals(dept.getId())) {
            throw new CustomException(DEPT_PARENT_ERROR);
        }
        Dept parent = this.getById(dept.getParentId());
        if (parent == null) {
            throw new CustomException(DEPT_PARENT_NOT_EXISTS);
        }
        List<Tree<Long>> allDept = this.getDeptSimpleList();
        TreeUtil.getParentsId()
        return dept;
    }

    /**
     * 校验部门是否存在
     *
     * @param id 部门 ID
     * @return 部门
     */
    private Dept checkDeptExists(Long id) {
        if (id == null) {
            return null;
        }
        Dept dept = this.getById(id);
        if (dept == null) {
            throw new CustomException(DEPT_NOT_EXISTS);
        }
        return dept;
    }
}
