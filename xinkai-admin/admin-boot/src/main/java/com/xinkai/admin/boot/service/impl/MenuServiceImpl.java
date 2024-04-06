package com.xinkai.admin.boot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.xinkai.admin.boot.mapper.MenuMapper;
import com.xinkai.admin.boot.pojo.dto.MenuRoutesDTO;
import com.xinkai.admin.boot.pojo.entity.MenuEntity;
import com.xinkai.admin.boot.pojo.entity.PermissionEntity;
import com.xinkai.admin.boot.pojo.entity.RoleEntity;
import com.xinkai.admin.boot.pojo.entity.RoleMenuEntity;
import com.xinkai.admin.boot.pojo.vo.MenuPermVO;
import com.xinkai.admin.boot.pojo.vo.MenuVO;
import com.xinkai.admin.boot.service.MenuService;
import com.xinkai.common.core.constant.GlobalConstants;
import com.xinkai.common.mybatis.base.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @className: MenuServiceImpl
 * @description: 服务实现类
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuMapper menuMapper;

    /**
     * 列表
     *
     * @param name 名字
     * @return {@link List}<{@link MenuVO}>
     */
    @Override
    public List<MenuVO> list(String name) {
        List<MenuEntity> menus = menuMapper.selectList(new LambdaQueryWrapper<MenuEntity>()
                .like(StrUtil.isNotEmpty(name), MenuEntity::getName, name)
                .orderByDesc(MenuEntity::getSort)
        );
        Set<Long> cacheMenuIds = menus.stream().map(MenuEntity::getId).collect(Collectors.toSet());

        return menus.stream().map(menu -> {
            Long parentId = menu.getParentId();
            // parentId不在当前菜单ID的列表，说明为顶级菜单ID，根据此ID作为递归的开始条件节点
            if (!cacheMenuIds.contains(parentId)) {
                cacheMenuIds.add(parentId);
                return recurTableMenus(parentId, menus);
            }
            return new LinkedList<MenuVO>();
        }).collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
    }

    /**
     * 获取路线
     * 获取用户导航栏菜单信息
     *
     * @return {@link List}<{@link Tree}<{@link Long}>>
     */
    @Override
    public List<Tree<Long>> routes() {
        List<MenuRoutesDTO> menuRoutes = menuMapper.selectJoinList(MenuRoutesDTO.class, new MPJLambdaWrapper<MenuEntity>()
                .selectAs(MenuEntity::getId, MenuRoutesDTO::getId)
                .selectAs(MenuEntity::getName, MenuRoutesDTO::getName)
                .selectAs(MenuEntity::getParentId, MenuRoutesDTO::getParentId)
                .selectAs(MenuEntity::getPath, MenuRoutesDTO::getPath)
                .selectAs(MenuEntity::getComponent, MenuRoutesDTO::getComponent)
                .selectAs(MenuEntity::getIcon, MenuRoutesDTO::getIcon)
                .selectAs(MenuEntity::getSort, MenuRoutesDTO::getSort)
                .selectAs(MenuEntity::getVisible, MenuRoutesDTO::getVisible)
                .selectAs(MenuEntity::getRedirect, MenuRoutesDTO::getRedirect)
                .selectAs(MenuEntity::getType, MenuRoutesDTO::getType)
                .selectCollection(MenuRoutesDTO::getRoles, map -> map
                        .result(RoleEntity::getCode))
                .leftJoin(RoleMenuEntity.class, RoleMenuEntity::getMenuId, MenuEntity::getId)
                .leftJoin(RoleEntity.class, RoleEntity::getId, RoleMenuEntity::getRoleId)
                .orderByDesc(MenuEntity::getSort)
        );
        return TreeUtil.
                build(menuRoutes, 0L, (treeNode, tree) -> {
                    tree.setId(treeNode.getId());
                    tree.setName(treeNode.getName());
                    tree.setParentId(treeNode.getParentId());
                    tree.putExtra("path", treeNode.getPath());
                    tree.putExtra("component", treeNode.getComponent());
                    tree.putExtra("redirect", treeNode.getRedirect());
                    tree.putExtra("meta", new MenuRoutesDTO.Meta()
                            .setTitle(treeNode.getName())
                            .setIcon(treeNode.getIcon())
                            .setRoles(treeNode.getRoles())
                            .setHidden(!GlobalConstants.STATUS_YES.equals(treeNode.getVisible()))
                            .setKeepAlive(true)
                    );
                });
    }

    /**
     * 获取菜单资源
     *
     * @return {@link MenuPermVO}
     */
    @Override
    public MenuPermVO getMenuResources() {
        List<MenuEntity> menuList = new MenuEntity().selectList(new LambdaQueryWrapper<MenuEntity>().orderByAsc(MenuEntity::getSort));
        List<PermissionEntity> permList = new PermissionEntity().selectAll();

        MenuPermVO resource = new MenuPermVO();
        List<MenuPermVO.MenuOption> menus = recurResources(GlobalConstants.ROOT_MENU_ID, menuList, permList);
        resource.setMenus(menus);

        List<MenuPermVO.PermOption> perms = Optional.ofNullable(permList).orElse(new ArrayList<>()).stream()
                .map(perm -> new MenuPermVO.PermOption(perm.getMenuId(), perm.getId(), perm.getName()))
                .collect(Collectors.toList());
        resource.setPerms(perms);

        return resource;
    }

    /**
     * 获取菜单选项
     *
     * @return {@link Option}<{@link String}>
     */
    @Override
    public List<Option<Long>> getMenuOptions() {
        List<MenuEntity> menuEntities = menuMapper.selectList(new LambdaQueryWrapper<MenuEntity>()
                .orderByAsc(MenuEntity::getSort));
        return recurMenus(GlobalConstants.ROOT_MENU_ID, menuEntities);
    }

    /**
     * 添加菜单
     *
     * @param menuEntity 菜单实体
     * @return boolean
     */
    @Override
    public boolean addMenu(MenuEntity menuEntity) {
        return false;
    }

    /**
     * 递归资源
     *
     * @param parentId 父ID
     * @param menuList 菜单列表
     * @param permList 烫发单
     * @return {@link List}<{@link MenuPermVO.MenuOption}>
     */
    private static List<MenuPermVO.MenuOption> recurResources(Long parentId, List<MenuEntity> menuList, List<PermissionEntity> permList) {
        return Optional.ofNullable(menuList).orElse(new ArrayList<>()).stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> {
                    Long menuId = menu.getId();
                    MenuPermVO.MenuOption menuOption = new MenuPermVO.MenuOption();
                    menuOption.setValue(menu.getId());
                    menuOption.setLabel(menu.getName());
                    List<MenuPermVO.MenuOption> children = recurResources(menu.getId(), menuList, permList);
                    long count = permList.stream().filter(perm -> perm.getMenuId().equals(menuId)).count();
                    // 如果该菜单下有权限，添加一个节点存放权限数据
                    if (count > 0) {
                        MenuPermVO.MenuOption permOption = new MenuPermVO.MenuOption();
                        permOption.setIsPerm(true);
                        permOption.setValue(-1L);
                        permOption.setPermPid(menuId);
                        children.add(permOption);
                    }
                    menuOption.setChildren(children);
                    return menuOption;
                }).collect(Collectors.toList());
    }

    /**
     * 重复表格菜单
     *
     * @param parentId 父ID
     * @param menuList 菜单列表
     * @return {@link List}<{@link MenuVO}>
     */
    private static List<MenuVO> recurTableMenus(Long parentId, List<MenuEntity> menuList) {
        return Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> {
                    MenuVO menuVO = new MenuVO();
                    BeanUtil.copyProperties(menu, menuVO);
                    List<MenuVO> children = recurTableMenus(menu.getId(), menuList);
                    menuVO.setChildren(children);
                    return menuVO;
                }).collect(Collectors.toList());
    }

    /**
     * 递归生成菜单下拉层级列表
     *
     * @param parentId 父级ID
     * @param menuList 菜单列表
     * @return {@link List}<{@link Option}<{@link Long}>>
     */
    private static List<Option<Long>> recurMenus(Long parentId, List<MenuEntity> menuList) {
        return Optional.ofNullable(menuList).orElse(new ArrayList<>()).stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> new Option<>(menu.getId(), menu.getName(), recurMenus(menu.getId(), menuList)))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}