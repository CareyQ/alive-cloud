package com.careyq.alive.module.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.core.domain.EntryVO;
import com.careyq.alive.mybatis.core.service.IServiceX;
import com.careyq.alive.module.system.dto.RoleDTO;
import com.careyq.alive.module.system.dto.RolePageDTO;
import com.careyq.alive.module.system.entity.Role;
import com.careyq.alive.module.system.vo.RoleVO;

import java.util.List;

/**
 * 角色服务
 *
 * @author CareyQ
 */
public interface RoleService extends IServiceX<Role> {

    /**
     * 保存角色
     *
     * @param dto 角色
     * @return 角色 ID
     */
    Long saveRole(RoleDTO dto);

    /**
     * 获取角色分页
     *
     * @param dto 角色筛选项
     * @return 角色分页
     */
    IPage<RoleVO> getRolePage(RolePageDTO dto);

    /**
     * 获取角色详情
     *
     * @param id 角色 ID
     * @return 角色
     */
    RoleVO getRoleDetail(Long id);

    /**
     * 删除角色
     *
     * @param id 角色 ID
     */
    void delRole(Long id);

    /**
     * 获取简单角色列表
     *
     * @return 角色列表
     */
    List<EntryVO> getRoleSimpleList();

    /**
     * 改变是否是默认
     *
     * @param id        角色 ID
     * @param isDefault 是否是默认
     */
    void changeDefault(Long id, Boolean isDefault);
}
