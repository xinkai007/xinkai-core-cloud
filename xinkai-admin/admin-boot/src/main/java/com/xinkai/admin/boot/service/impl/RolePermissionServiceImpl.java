package com.xinkai.admin.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinkai.admin.boot.mapper.RolePermissionMapper;
import com.xinkai.admin.boot.pojo.entity.RolePermissionEntity;
import com.xinkai.admin.boot.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xinkai
 * @className com.xinkai.admin.boot.service.impl.RolePermissionServiceImpl
 * @description 角色权限服务实施
 * @email xinkai8011@gmail.com
 * @date 2023/11/30
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermissionEntity> implements RolePermissionService {
    private final RolePermissionMapper rolePermissionMapper;
}