package com.xinkai.admin.boot.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.xinkai.admin.boot.converter.RoleConverter;
import com.xinkai.admin.boot.mapper.RoleMapper;
import com.xinkai.admin.boot.mapper.RoleMenuMapper;
import com.xinkai.admin.boot.mapper.RolePermissionMapper;
import com.xinkai.admin.boot.mapper.UserRoleMapper;
import com.xinkai.admin.boot.pojo.dto.RoleDTO;
import com.xinkai.admin.boot.pojo.dto.RoleMenuPermDTO;
import com.xinkai.admin.boot.pojo.entity.*;
import com.xinkai.admin.boot.pojo.from.RoleForm;
import com.xinkai.admin.boot.pojo.query.RoleListQuery;
import com.xinkai.admin.boot.pojo.query.RoleOptionsQuery;
import com.xinkai.admin.boot.pojo.vo.RoleInfoVO;
import com.xinkai.admin.boot.pojo.vo.RoleMenuPermVO;
import com.xinkai.admin.boot.pojo.vo.RoleOptionsVO;
import com.xinkai.admin.boot.service.PermissionService;
import com.xinkai.admin.boot.service.RoleMenuService;
import com.xinkai.admin.boot.service.RolePermissionService;
import com.xinkai.admin.boot.service.RoleService;
import com.xinkai.common.core.exception.SystemException;
import com.xinkai.common.core.result.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.xinkai.common.core.constant.GlobalConstants.STATUS_YES;

/**
 * @className: RoleServiceImpl
 * @description: 服务实现类
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {
    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;
    private final RoleMenuMapper roleMenuMapper;
    private final RolePermissionMapper rolePermissionMapper;
    private final PermissionService permissionService;
    private final RoleMenuService roleMenuService;
    private final RolePermissionService rolePermissionService;
    private final RoleConverter roleConverter;

    /**
     * 获取角色列表
     *
     * @param roleOptionsQuery 角色选择查询
     * @return {@link IPage}<{@link RoleOptionsVO}>
     */
    @Override
    @SneakyThrows
    public IPage<RoleOptionsVO> list(RoleOptionsQuery roleOptionsQuery) {
        try {
            String name = roleOptionsQuery.getKeywords();
            return roleMapper.selectJoinPage(new Page<>(roleOptionsQuery.getPageNum(), roleOptionsQuery.getPageSize()),
                    RoleOptionsVO.class,
                    new MPJLambdaWrapper<RoleEntity>()
                            .selectAs(RoleEntity::getId, RoleOptionsVO::getId)
                            .selectAs(RoleEntity::getName, RoleOptionsVO::getName)
                            .eq(UserEntity::getStatus, STATUS_YES)
                            .and(CharSequenceUtil.isNotEmpty(name), e -> e.like(RoleEntity::getName, name)));
        } catch (Exception e) {
            log.error("RoleServiceImpl.list e:", e);
            throw new SystemException(ResultCode.SYSTEM_EXECUTION_ERROR);
        }
    }

    /**
     * 角色列表查询
     *
     * @param roleListQuery 角色列表查询
     * @return {@link IPage}<{@link RoleInfoVO}>
     */
    @Override
    @SneakyThrows
    public IPage<RoleInfoVO> pages(RoleListQuery roleListQuery) {
        try {
            String keywords = roleListQuery.getKeywords();
            return roleMapper.selectJoinPage(new Page<>(roleListQuery.getPageNum(), roleListQuery.getPageSize()),
                    RoleInfoVO.class,
                    new MPJLambdaWrapper<RoleEntity>()
                            .selectAs(RoleEntity::getId, RoleInfoVO::getId)
                            .selectAs(RoleEntity::getName, RoleInfoVO::getName)
                            .selectAs(RoleEntity::getCode, RoleInfoVO::getCode)
                            .like(CharSequenceUtil.isNotEmpty(keywords), RoleEntity::getName, keywords)
                            .or()
                            .like(CharSequenceUtil.isNotEmpty(keywords), RoleEntity::getCode, keywords)
            );
        } catch (Exception e) {
            log.error("UserServiceImpl.listQuery e:", e);
            throw new SystemException(ResultCode.SYSTEM_EXECUTION_ERROR);
        }
    }

    /**
     * 角色详情
     *
     * @param roleId 角色ID
     * @return {@link RoleInfoVO}
     */
    @Override
    public RoleInfoVO detail(Long roleId) {
        RoleEntity roleEntity = roleMapper.selectById(roleId);
        return new RoleInfoVO()
                .setId(roleEntity.getId())
                .setName(roleEntity.getName())
                .setCode(roleEntity.getCode())
                .setSort(roleEntity.getSort())
                .setDeleted(roleEntity.getIsDelete())
                .setStatus(roleEntity.getStatus());
    }

    /**
     * 更新角色
     *
     * @param roleDTO 角色Dto
     * @return {@link Boolean}
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean update(RoleDTO roleDTO) {
//        return new RoleEntity()
//                .setId(roleDTO.getId())
//                .setName(roleDTO.getName())
//                .setCode(roleDTO.getCode())
//                .setSort(roleDTO.getSort())
//                .setStatus(roleDTO.getStatus())
//                .updateById();
        return null;
    }

    /**
     * 删除角色
     *
     * @param ids 身份证
     * @return {@link Boolean}
     */
    @Override
    public Boolean delete(String ids) {
        List<Long> roleIds = Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
        //循环删除与角色相关信息
        Optional.of(roleIds)
                .orElse(new ArrayList<>())
                .forEach(id -> {
                    Long count = userRoleMapper.selectCount(new LambdaQueryWrapper<UserRoleEntity>().eq(UserRoleEntity::getRoleId, id));
                    Assert.isTrue(count <= 0, "该角色已分配用户，无法删除");
                    roleMenuMapper.delete(new LambdaQueryWrapper<RoleMenuEntity>().eq(RoleMenuEntity::getRoleId, id));
                    rolePermissionMapper.delete(new LambdaQueryWrapper<RolePermissionEntity>().eq(RolePermissionEntity::getRoleId, id));
                });
        int i = roleMapper.deleteBatchIds(roleIds);
        //删除成功，刷新权限缓存
        if (i > 0) {
            return permissionService.refreshPermRolesRules();
        }
        return true;
    }

    /**
     * 获取角色资源
     *
     * @param roleId 角色ID
     * @return {@link RoleMenuPermVO}
     */
    @Override
    public RoleMenuPermVO getRoleResources(Long roleId) {
        // 获取角色拥有的菜单ID集合
        List<Long> menuIds = roleMenuMapper.listMenuIdsByRoleId(roleId);
        // 获取角色拥有的权限ID集合
        List<Long> permIds = rolePermissionMapper.selectJoinList(Long.class, new MPJLambdaWrapper<RolePermissionEntity>()
                .select(RolePermissionEntity::getPermissionId)
                .innerJoin(PermissionEntity.class, PermissionEntity::getId, RolePermissionEntity::getPermissionId)
                .eq(RolePermissionEntity::getRoleId, roleId)
        );
        return new RoleMenuPermVO().setMenuIds(menuIds).setPermIds(permIds);
    }

    /**
     * 修改角色的资源权限
     *
     * @param roleId          角色ID
     * @param roleMenuPermDTO 角色菜单权限dto
     * @return boolean
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean updateRoleResource(Long roleId, RoleMenuPermDTO roleMenuPermDTO) {
        // 删除角色菜单
        roleMenuService.remove(new LambdaQueryWrapper<RoleMenuEntity>().eq(RoleMenuEntity::getRoleId, roleId));
        // 新增角色菜单
        List<Long> menuIds = roleMenuPermDTO.getMenuIds();
        if (CollectionUtil.isNotEmpty(menuIds)) {
            List<RoleMenuEntity> roleMenus = menuIds.stream().map(menuId -> new RoleMenuEntity()
                    .setRoleId(roleId)
                    .setMenuId(menuId)
            ).collect(Collectors.toList());
            roleMenuService.saveBatch(roleMenus);
        }
        // 删除角色权限
        rolePermissionService.remove(new LambdaQueryWrapper<RolePermissionEntity>().eq(RolePermissionEntity::getRoleId, roleId));
        // 新增角色权限
        List<Long> permIds = roleMenuPermDTO.getPermIds();
        if (CollectionUtil.isNotEmpty(permIds)) {
            List<RolePermissionEntity> rolePerms = permIds.stream().map(permId -> new RolePermissionEntity()
                    .setRoleId(roleId)
                    .setPermissionId(permId)
            ).collect(Collectors.toList());
            rolePermissionService.saveBatch(rolePerms);
        }
        permissionService.refreshPermRolesRules();
        return true;
    }

    /**
     * 保存角色
     *
     * @param roleForm 角色表单
     * @return boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRole(RoleForm roleForm) {
        Long roleId = roleForm.getId();
        String roleCode = roleForm.getCode();

        long count = this.count(new LambdaQueryWrapper<RoleEntity>()
                .ne(roleId != null, RoleEntity::getId, roleId)
                .and(wrapper ->
                        wrapper.eq(RoleEntity::getCode, roleCode).or().eq(RoleEntity::getName, roleCode)
                ));
        Assert.isTrue(count == 0, "角色名称或角色编码重复，请检查！");

        // 实体转换
        RoleEntity role = roleConverter.form2Entity(roleForm);

        boolean result = this.saveOrUpdate(role);
        // 刷新权限缓存
        if (result) {
            permissionService.refreshPermRolesRules();
        }
        return result;
    }
}