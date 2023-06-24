package com.xinkai.admin.boot.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.xinkai.admin.boot.mapper.MenuMapper;
import com.xinkai.admin.boot.pojo.dto.MenuRoutesDTO;
import com.xinkai.admin.boot.pojo.entity.MenuEntity;
import com.xinkai.admin.boot.pojo.entity.RoleEntity;
import com.xinkai.admin.boot.pojo.entity.RoleMenuEntity;
import com.xinkai.admin.boot.service.MenuService;
import com.xinkai.common.core.constant.GlobalConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return TreeUtil.<MenuRoutesDTO, Long>
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
}