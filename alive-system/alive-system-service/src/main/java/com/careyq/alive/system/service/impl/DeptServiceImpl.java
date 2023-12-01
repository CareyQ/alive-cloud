package com.careyq.alive.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.core.enums.CommonStatusEnum;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.core.util.TreeUtils;
import com.careyq.alive.system.dto.DeptDTO;
import com.careyq.alive.system.dto.DeptSearchDTO;
import com.careyq.alive.system.entity.Dept;
import com.careyq.alive.system.entity.User;
import com.careyq.alive.system.mapper.DeptMapper;
import com.careyq.alive.system.mapper.UserMapper;
import com.careyq.alive.system.service.DeptService;
import com.careyq.alive.system.vo.DeptVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.careyq.alive.system.constants.SystemResultCode.*;

/**
 * 部门服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    private final UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveDept(DeptDTO dto) {
        // 检查部门名称是否已存在
        boolean exists = this.lambdaQuery()
                .ne(dto.getId() != null, Dept::getId, dto.getId())
                .eq(Dept::getName, dto.getName())
                .exists();
        if (exists) {
            throw new CustomException(DEPT_NAME_DUPLICATE);
        }
        if (dto.getId() != null) {
            this.checkDeptExists(dto.getId());
        }
        this.checkDeptParent(dto.getId(), dto.getParentId());
        Dept dept = BeanUtil.copyProperties(dto, Dept.class);
        this.saveOrUpdate(dept);
        return dept.getId();
    }

    @Override
    public List<Tree<Long>> getDeptList(DeptSearchDTO dto) {
        List<Dept> deptList = this.lambdaQuery()
                .like(StrUtil.isNotBlank(dto.getName()), Dept::getName, dto.getName())
                .eq(dto.getStatus() != null, Dept::getStatus, dto.getStatus())
                .orderByAsc(Dept::getSort)
                .list();
        Set<Long> managerIds = deptList.stream().map(Dept::getManagerId).collect(Collectors.toSet());
        Map<Long, String> userMap = MapUtil.newHashMap();
        if (!managerIds.isEmpty()) {
            userMap = userMapper.selectList(new LambdaQueryWrapper<User>()
                            .in(User::getId, managerIds)).stream()
                    .collect(Collectors.toMap(User::getId, User::getNickname));
        }
        final Map<Long, String> finalUserMap = userMap;
        return TreeUtil.build(deptList, Dept.ROOT_ID, (node, tree) -> {
            tree.setId(node.getId())
                    .setParentId(node.getParentId())
                    .setName(node.getName());
            tree.put("sort", node.getSort());
            tree.put("managerName", finalUserMap.get(node.getManagerId()));
            tree.put("mobile", node.getMobile());
            tree.put("remark", node.getRemark());
            tree.put("status", node.getStatus());
        });
    }

    @Override
    public DeptVO getDeptDetail(Long id) {
        Dept dept = this.checkDeptExists(id);
        DeptVO res = BeanUtil.copyProperties(dept, DeptVO.class);
        if (res.getParentId() == 0) {
            res.setParentId(null);
        }
        if (dept.getManagerId() != null) {
            User user = userMapper.selectById(dept.getManagerId());
            res.setManagerName(user.getNickname());
        }
        return res;
    }

    @Override
    public void delDept(Long id) {
        this.checkDeptExists(id);
        // 是否存在子部门
        if (this.lambdaQuery().eq(Dept::getParentId, id).exists()) {
            throw new CustomException(DEPT_EXISTS_CHILDREN);
        }
        // 部门下是否还有人员
        if (userMapper.exists(new LambdaQueryWrapper<User>().eq(User::getDeptId, id))) {
            throw new CustomException(DEPT_HAS_USER);
        }
        this.removeById(id);
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
     * @param id       部门 ID
     * @param parentId 父级部门 ID
     */
    private void checkDeptParent(Long id, Long parentId) {
        if (parentId == null || id == null) {
            return;
        }
        if (parentId.equals(id)) {
            throw new CustomException(DEPT_PARENT_ERROR);
        }
        Dept parent = this.getById(parentId);
        if (parent == null) {
            throw new CustomException(DEPT_PARENT_NOT_EXISTS);
        }
        List<Tree<Long>> allDept = this.getDeptSimpleList();
        List<Tree<Long>> childList = TreeUtils.treeToList(allDept, id);
        if (childList.stream().anyMatch(e -> e.getId().equals(parentId))) {
            throw new CustomException(DEPT_PARENT_IS_CHILD);
        }
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

    @Override
    public List<Dept> getDeptList(Collection<Long> ids) {
        if (CollUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }
        return this.listByIds(ids);
    }
}
