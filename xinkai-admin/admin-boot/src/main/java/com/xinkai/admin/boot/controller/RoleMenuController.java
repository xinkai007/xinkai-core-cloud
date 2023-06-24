package com.xinkai.admin.boot.controller;

import com.xinkai.admin.boot.service.RoleMenuService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: RoleMenuController
 * @description: 控制层
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Api(tags = "角色和菜单关联表对象功能接口")
@RestController
@RequestMapping("/api/v1/role_menu")
@RequiredArgsConstructor
public class RoleMenuController {
    private final RoleMenuService roleMenuService;
}