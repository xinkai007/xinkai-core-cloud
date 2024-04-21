package com.xinkai.admin.boot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinkai.admin.boot.pojo.entity.PermissionEntity;
import com.xinkai.admin.boot.pojo.query.PermPageQuery;
import com.xinkai.admin.boot.pojo.vo.PermPageVO;
import com.xinkai.admin.boot.service.PermissionService;
import com.xinkai.common.core.result.PageResult;
import com.xinkai.common.core.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: PermissionController
 * @description: 控制层
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Api(tags = "权限表对象功能接口")
@RestController
@RequestMapping("/api/v1/permission")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @ApiOperation(value = "权限分页列表")
    @GetMapping("/page")
    public PageResult<PermPageVO> listPermPages(PermPageQuery permPageQuery) {
        IPage<PermPageVO> result = permissionService.listPermPages(permPageQuery);
        return PageResult.success(result);
    }

    @ApiOperation(value = "权限详情")
    @GetMapping("/{permissionId}")
    public Result<PermissionEntity> getPermissionDetail(@ApiParam("权限ID") @PathVariable Long permissionId) {
        PermissionEntity permission = permissionService.getById(permissionId);
        return Result.success(permission);
    }
}