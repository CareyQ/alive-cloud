package com.careyq.alive.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.alive.system.entity.Menu;
import com.careyq.alive.system.vo.MenuVO;

import java.util.List;
import java.util.Set;

/**
 * 菜单权限服务
 *
 * @author CareyQ
 * @since 2023-11-17
 */
public interface MenuService extends IService<Menu> {

    /**
     * 根据角色 ID 获取菜单树
     *
     * @param roleIds 角色 ID，非必填，不传获取所有
     * @return 菜单树
     */
    List<MenuVO> getMenuTree(Set<Long> roleIds);
}
