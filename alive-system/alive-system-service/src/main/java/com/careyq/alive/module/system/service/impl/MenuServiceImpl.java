package com.careyq.alive.module.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.careyq.alive.core.enums.CommonStatusEnum;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.mybatis.core.service.impl.ServiceImplX;
import com.careyq.alive.satoken.AuthHelper;
import com.careyq.alive.satoken.core.domain.LoginUser;
import com.careyq.alive.module.system.entity.Menu;
import com.careyq.alive.module.system.enums.MenuTypeEnum;
import com.careyq.alive.module.system.mapper.MenuMapper;
import com.careyq.alive.module.system.mapper.RoleMenuMapper;
import com.careyq.alive.module.system.service.MenuService;
import com.careyq.alive.module.system.vo.MenuVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.careyq.alive.module.system.constants.SystemResultCode.*;

/**
 * 菜单权限服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class MenuServiceImpl extends ServiceImplX<MenuMapper, Menu> implements MenuService {

    private final RoleMenuMapper roleMenuMapper;

    @Override
    public List<Tree<Long>> getMenuTree(boolean isRouter) {
        List<String> permission = null;
        if (isRouter) {
            LoginUser loginUser = AuthHelper.getLoginUser();
            permission = loginUser.getPermission();
        }
        List<Menu> menus = this.lambdaQueryX()
                .inIfPresent(Menu::getPermission, permission)
                .ne(isRouter, Menu::getType, MenuTypeEnum.BUTTON.getType())
                .orderByAsc(Menu::getSort)
                .list();

        return TreeUtil.build(menus, Menu.ROOT_ID, (node, tree) -> {
            tree.setId(node.getId())
                    .setParentId(node.getParentId())
                    .setName(node.getName());
            if (isRouter) {
                tree.putExtra("component", node.getComponent());
                tree.putExtra("componentName", node.getComponentName());
                tree.putExtra("icon", node.getIcon());
            } else {
                tree.putExtra("permission", node.getPermission());
                tree.putExtra("sort", node.getSort());
                tree.putExtra("type", node.getType());
                tree.putExtra("status", node.getStatus());
            }
            tree.putExtra("path", node.getPath());
            tree.putExtra("keepAlive", node.getKeepAlive());
        });
    }

    @Override
    public MenuVO getMenuDetail(Long id) {
        Menu menu = this.getById(id);
        if (menu.getParentId() == 0) {
            menu.setParentId(null);
        }
        return BeanUtil.copyProperties(menu, MenuVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveMenu(MenuVO menuVo) {
        this.validMenu(menuVo);
        Menu menu = BeanUtil.copyProperties(menuVo, Menu.class);
        // 目录路由地址开头携带斜杠 "/"，菜单地址不携带
        if (MenuTypeEnum.DIR.getType().equals(menu.getType()) && !Validator.isUrl(menu.getPath())) {
            if (!menu.getPath().startsWith(StrUtil.SLASH)) {
                menu.setPath(StrUtil.SLASH + menu.getPath());
            }
        } else if (MenuTypeEnum.MENU.getType().equals(menu.getType())) {
            if (menu.getPath().startsWith(StrUtil.SLASH)) {
                menu.setPath(menu.getPath().replaceFirst(StrUtil.SLASH, StrUtil.EMPTY));
            }
        }
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
        if (menu.getParentId() != null && menu.getParentId().equals(menu.getId())) {
            throw new CustomException(MENU_PARENT_SELF);
        }
        // 不是目录校验父级菜单
        if (!MenuTypeEnum.DIR.getType().equals(menu.getType())) {
            if (menu.getParentId() == null || this.getById(menu.getParentId()) == null) {
                throw new CustomException(MENU_PARENT_NOT_EXISTS);
            }
        }
        // 校验菜单名称是否已存在，同层级下不可重复
        boolean exists = this.lambdaQueryX()
                .neIfPresent(Menu::getId, menu.getId())
                .eq(Menu::getName, menu.getName())
                .eq(Menu::getParentId, menu.getParentId())
                .exists();
        if (exists) {
            throw new CustomException(MENU_NAME_DUPLICATE);
        }
    }

    @Override
    public void delMenu(Long id) {
        boolean existsChild = this.lambdaQuery()
                .eq(Menu::getParentId, id)
                .exists();
        if (existsChild) {
            throw new CustomException(MENU_EXISTS_CHILDREN);
        }
        this.removeById(id);
        roleMenuMapper.delByMenu(id);
    }

    @Override
    public List<Tree<Long>> getMenuSimpleTree() {
        List<Menu> menus = this.lambdaQuery()
                .eq(Menu::getStatus, CommonStatusEnum.ENABLE.getStatus())
                .orderByAsc(Menu::getSort)
                .list();
        return TreeUtil.build(menus, Menu.ROOT_ID, (node, tree) ->
                tree.setId(node.getId())
                        .setParentId(node.getParentId())
                        .setName(node.getName()));
    }
}
