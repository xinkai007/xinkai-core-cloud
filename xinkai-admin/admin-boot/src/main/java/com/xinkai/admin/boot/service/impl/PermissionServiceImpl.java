package com.xinkai.admin.boot.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.xinkai.admin.boot.mapper.PermissionMapper;
import com.xinkai.admin.boot.pojo.dto.PermRoleDTO;
import com.xinkai.admin.boot.pojo.entity.PermissionEntity;
import com.xinkai.admin.boot.pojo.entity.RoleEntity;
import com.xinkai.admin.boot.pojo.entity.RolePermissionEntity;
import com.xinkai.admin.boot.pojo.query.PermPageQuery;
import com.xinkai.admin.boot.pojo.vo.PermPageVO;
import com.xinkai.admin.boot.service.PermissionService;
import com.xinkai.common.core.constant.GlobalConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @className: PermissionServiceImpl
 * @description: 服务实现类
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntity> implements PermissionService {
    private final PermissionMapper permissionMapper;

    private final RedisTemplate redisTemplate;

    /**
     * 刷新Redis缓存中角色菜单的权限规则
     * 角色和菜单信息变更调用
     *
     * @return {@link Boolean}
     */
    @Override
    public Boolean refreshPermRolesRules() {
        redisTemplate.delete(Arrays.asList(GlobalConstants.URL_PERM_ROLES_KEY, GlobalConstants.BTN_PERM_ROLES_KEY));
        List<PermRoleDTO> permissions = this.getPermRoles();
        if (CollUtil.isNotEmpty(permissions)) {
            // 初始化URL【权限->角色(集合)】规则
            List<PermRoleDTO> urlPermList = permissions.stream()
                    .filter(item -> CharSequenceUtil.isNotBlank(item.getUrlPerm()))
                    .collect(Collectors.toList());
            if (CollUtil.isNotEmpty(urlPermList)) {
                Map<String, List<String>> urlPermRoles = new HashMap<>(12);
                urlPermList.forEach(item -> {
                    String perm = item.getUrlPerm();
                    List<String> roles = item.getRoles();
                    urlPermRoles.put(perm, roles);
                });
                redisTemplate.opsForHash().putAll(GlobalConstants.URL_PERM_ROLES_KEY, urlPermRoles);
                redisTemplate.convertAndSend("cleanRoleLocalCache", "true");
            }
            // 初始化URL【按钮->角色(集合)】规则
            List<PermRoleDTO> btnPermList = permissions.stream()
                    .filter(item -> CharSequenceUtil.isNotBlank(item.getBtnPerm()))
                    .collect(Collectors.toList());
            if (CollUtil.isNotEmpty(btnPermList)) {
                Map<String, List<String>> btnPermRoles = MapUtil.newHashMap();
                btnPermList.stream().forEach(item -> {
                    String perm = item.getBtnPerm();
                    List<String> roles = item.getRoles();
                    btnPermRoles.put(perm, roles);
                });
                redisTemplate.opsForHash().putAll(GlobalConstants.BTN_PERM_ROLES_KEY, btnPermRoles);
            }
        }
        return true;
    }

    /**
     * 获取角色权限信息
     *
     * @return {@link List}<{@link PermRoleDTO}>
     */
    public List<PermRoleDTO> getPermRoles() {
        return permissionMapper.selectJoinList(PermRoleDTO.class,
                new MPJLambdaWrapper<PermissionEntity>()
                        .selectAs(PermissionEntity::getId, PermRoleDTO::getId)
                        .selectAs(PermissionEntity::getName, PermRoleDTO::getName)
                        .selectAs(PermissionEntity::getBtnPerm, PermRoleDTO::getBtnPerm)
                        .selectAs(PermissionEntity::getMenuId, PermRoleDTO::getMenuId)
                        .selectAs(PermissionEntity::getUrlPerm, PermRoleDTO::getUrlPerm)
                        .selectCollection(PermRoleDTO::getRoles, map -> map
                                .result(RoleEntity::getCode))
                        .leftJoin(RolePermissionEntity.class, RolePermissionEntity::getPermissionId, PermissionEntity::getId)
                        .leftJoin(RoleEntity.class, RoleEntity::getId, RolePermissionEntity::getRoleId)
        );
    }

    /**
     * 获取权限分页列表
     *
     * @param queryParams 查询参数
     * @return {@link IPage}<{@link PermPageVO}>
     */
    @Override
    public IPage<PermPageVO> listPermPages(PermPageQuery queryParams) {
        Page<PermPageVO> page = new Page<>(queryParams.getPageNum(), queryParams.getPageSize());
        return page.setRecords(this.baseMapper.listPermPages(page, queryParams));
    }
}