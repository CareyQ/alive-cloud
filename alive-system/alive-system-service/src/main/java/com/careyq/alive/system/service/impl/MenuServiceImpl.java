package com.careyq.alive.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.core.domain.EntryVO;
import com.careyq.alive.core.enums.CommonStatusEnum;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.system.entity.Menu;
import com.careyq.alive.system.enums.MenuTypeEnum;
import com.careyq.alive.system.mapper.MenuMapper;
import com.careyq.alive.system.service.MenuService;
import com.careyq.alive.system.vo.MenuVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.careyq.alive.system.constants.SystemResultCode.*;

/**
 * 菜单权限服务实现
 *
 * @author CareyQ
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<Tree<Long>> getMenuTree(Set<Long> roleIds, boolean isRouter) {
        LambdaQueryChainWrapper<Menu> wrapper = this.lambdaQuery()
                .ne(isRouter, Menu::getType, MenuTypeEnum.BUTTON.getType());
        if (CollUtil.isNotEmpty(roleIds)) {
            // todo
        }
        List<Menu> menus = wrapper.list();

        if (isRouter) {
            return TreeUtil.build(menus, Menu.ROOT_ID, (node, tree) -> {
                tree.setId(node.getId())
                        .setParentId(node.getParentId())
                        .setName(node.getName());
                tree.putExtra("path", node.getPath());
                tree.putExtra("component", node.getComponent());
                tree.putExtra("componentName", node.getComponentName());
                tree.putExtra("icon", node.getIcon());
                tree.putExtra("keepAlive", node.getKeepAlive());
            });
        }

        return TreeUtil.build(menus, Menu.ROOT_ID, (node, tree) -> {
            tree.setId(node.getId())
                    .setParentId(node.getParentId())
                    .setName(node.getName());
            tree.putExtra("path", StrUtil.prependIfMissing(node.getPath(), StrUtil.SLASH));
            tree.putExtra("permission", node.getPermission());
            tree.putExtra("sort", node.getSort());
            tree.putExtra("type", node.getType());
            tree.putExtra("status", node.getStatus());
            tree.putExtra("keepAlive", node.getKeepAlive());
        });
    }

    @Override
    public List<EntryVO> getListByType(Integer type) {
        return this.lambdaQuery()
                .eq(Menu::getType, type)
                .eq(Menu::getStatus, CommonStatusEnum.ENABLE.getStatus())
                .list().stream()
                .map(menu -> new EntryVO(menu.getId(), menu.getName()))
                .toList();
    }

    @Override
    public MenuVO getMenuDetail(Long id) {
        Menu menu = this.getById(id);
        return BeanUtil.copyProperties(menu, MenuVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveMenu(MenuVO menuVo) {
        this.validMenu(menuVo);
        Menu menu = BeanUtil.copyProperties(menuVo, Menu.class);
        this.saveOrUpdate(menu);
        return menu.getId();
    }

    /**
     * 校验菜单
     *
     * @param menu 菜单
     */
    private void validMenu(MenuVO menu) {
        if (menu.getId() != null && this.getById(menu.getId()) == null) {
            throw new CustomException(MENU_NOT_EXISTS);
        }
        // 不是目录校验父级菜单
        if (!MenuTypeEnum.DIR.getType().equals(menu.getType())) {
            if (menu.getParentId() == null || this.getById(menu.getParentId()) == null) {
                throw new CustomException(MENU_PARENT_NOT_EXISTS);
            }
        }
        // 校验菜单名称是否已存在，同层级下不可重复
        boolean exists = this.lambdaQuery()
                .eq(Menu::getName, menu.getName())
                .ne(menu.getId() != null, Menu::getId, menu.getId())
                .eq(Menu::getParentId, menu.getParentId())
                .exists();
        if (exists) {
            throw new CustomException(MENU_NAME_DUPLICATE);
        }
    }
}
