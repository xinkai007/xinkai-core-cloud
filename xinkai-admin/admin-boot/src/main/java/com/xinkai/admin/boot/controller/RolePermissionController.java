package com.xinkai.admin.boot.controller;

import com.xinkai.admin.boot.service.RolePermissionService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: RolePermissionController
 * @description: 控制层
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Api(tags = "角色权限表对象功能接口")
@RestController
@RequestMapping("/api/v1/role_permission")
@RequiredArgsConstructor
public class RolePermissionController {
    private final RolePermissionService rolePermissionService;
}