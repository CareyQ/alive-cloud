package com.careyq.alive.system.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.alive.core.domain.EntryVO;
import com.careyq.alive.system.entity.Menu;
import com.careyq.alive.system.vo.MenuVO;

import java.util.List;
import java.util.Set;

/**
 * 菜单权限服务
 *
 * @author CareyQ
 */
public interface MenuService extends IService<Menu> {

    /**
     * 根据角色 ID 获取菜单树
     *
     * @param roleIds  角色 ID，非必填，不传获取所有
     * @param isRouter 是否是路由
     * @return 菜单树
     */
    List<Tree<Long>> getMenuTree(Set<Long> roleIds, boolean isRouter);

    /**
     * 获取指定类型的菜单列表
     *
     * @param type 类型
     * @return 菜单列表
     */
    List<EntryVO> getListByType(Integer type);

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
}
