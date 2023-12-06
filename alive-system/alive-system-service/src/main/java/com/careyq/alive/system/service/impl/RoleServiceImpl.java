package com.careyq.alive.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.careyq.alive.core.domain.EntryVO;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.mybatis.core.service.impl.ServiceImplX;
import com.careyq.alive.system.convert.RoleConvert;
import com.careyq.alive.system.dto.RoleDTO;
import com.careyq.alive.system.dto.RolePageDTO;
import com.careyq.alive.system.entity.Role;
import com.careyq.alive.system.mapper.RoleMapper;
import com.careyq.alive.system.mapper.RoleMenuMapper;
import com.careyq.alive.system.mapper.RoleUserMapper;
import com.careyq.alive.system.service.RoleService;
import com.careyq.alive.system.vo.RoleVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.careyq.alive.system.constants.SystemResultCode.*;

/**
 * 角色服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl extends ServiceImplX<RoleMapper, Role> implements RoleService {

    private final RoleUserMapper roleUserMapper;
    private final RoleMenuMapper roleMenuMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveRole(RoleDTO dto) {
        // 检查角色名称是否已存在
        boolean exists = this.lambdaQueryX()
                .neIfPresent(Role::getId, dto.getId())
                .eq(Role::getName, dto.getName())
                .exists();
        if (exists) {
            throw new CustomException(ROLE_NAME_DUPLICATE);
        }
        if (dto.getId() != null) {
            this.checkRoleExists(dto.getId());
        }
        Role role = BeanUtil.copyProperties(dto, Role.class);
        this.saveOrUpdate(role);
        return role.getId();
    }

    @Override
    public IPage<RoleVO> getRolePage(RolePageDTO dto) {
        IPage<Role> page = this.lambdaQuery()
                .like(StrUtil.isNotBlank(dto.getName()), Role::getName, dto.getName())
                .orderByDesc(Role::getId)
                .page(new Page<>(dto.getCurrent(), dto.getSize()));
        return page.convert(RoleConvert.INSTANCE::convert);
    }

    @Override
    public RoleVO getRoleDetail(Long id) {
        Role role = this.checkRoleExists(id);
        return RoleConvert.INSTANCE.convert(role);
    }

    @Override
    public void delRole(Long id) {
        roleUserMapper.delByRole(id);
        roleMenuMapper.delByRole(id);
        this.removeById(id);
    }

    @Override
    public List<EntryVO> getRoleSimpleList() {
        return this.lambdaQuery()
                .list().stream()
                .map(e -> new EntryVO(e.getId(), e.getName()))
                .toList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeDefault(Long id, Boolean isDefault) {
        this.checkRoleExists(id);
        if (Boolean.TRUE.equals(isDefault)) {
            // 将其他默认角色进行移除
            this.lambdaUpdate().set(Role::getIsDefault, false).eq(Role::getIsDefault, true).update();
        } else {
            // 必须保留一个默认角色
            if (!this.lambdaQuery().eq(Role::getIsDefault, true).ne(Role::getId, id).exists()) {
                throw new CustomException(ROLE_DEFAULT_NOT_EXISTS);
            }
        }
        this.lambdaUpdate().set(Role::getIsDefault, isDefault).eq(Role::getId, id).update();
    }

    /**
     * 校验角色是否存在
     *
     * @param id 角色 ID
     * @return 角色
     */
    private Role checkRoleExists(Long id) {
        if (id == null) {
            return null;
        }
        Role role = this.getById(id);
        if (role == null) {
            throw new CustomException(ROLE_NOT_EXISTS);
        }
        return role;
    }

}
