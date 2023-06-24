package com.xinkai.admin.boot.controller;

import com.xinkai.admin.boot.service.UserRoleService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: UserRoleController
 * @description: 控制层
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Api(tags = "用户和角色关联表对象功能接口")
@RestController
@RequestMapping("/api/v1/user_role")
@RequiredArgsConstructor
public class UserRoleController {
    private final UserRoleService userRoleService;
}