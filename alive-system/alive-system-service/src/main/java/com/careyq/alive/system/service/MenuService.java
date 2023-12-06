package com.careyq.alive.system.service;

import cn.hutool.core.lang.tree.Tree;
import com.careyq.alive.mybatis.core.service.IServiceX;
import com.careyq.alive.system.entity.Menu;
import com.careyq.alive.system.vo.MenuVO;

import java.util.List;

/**
 * 菜单权限服务
 *
 * @author CareyQ
 */
public interface MenuService extends IServiceX<Menu> {

    /**
     * 根据角色 ID 获取菜单树
     *
     * @param isRouter 是否是路由
     * @return 菜单树
     */
    List<Tree<Long>> getMenuTree(boolean isRouter);

    /**
     * 获取菜单详情
     *
     * @param id 菜单 ID
     * @return 菜单详情
     */
    MenuVO getMenuDetail(Long id);

    /**
     * 保存菜单
     *
     * @param menuVo 菜单
     * @return 菜单 ID
     */
    Long saveMenu(MenuVO menuVo);

    /**
     * 删除菜单
     *
     * @param id 菜单 ID
     */
    void delMenu(Long id);

    /**
     * 获取菜单简单树，只有启用
     *
     * @return 菜单树
     */
    List<Tree<Long>> getMenuSimpleTree();
}
