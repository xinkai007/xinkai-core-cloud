package com.xinkai.admin.boot.controller;

import com.xinkai.admin.boot.pojo.dto.RoleDTO;
import com.xinkai.admin.boot.pojo.dto.RoleMenuPermDTO;
import com.xinkai.admin.boot.pojo.query.RoleListQuery;
import com.xinkai.admin.boot.pojo.query.RoleOptionsQuery;
import com.xinkai.admin.boot.pojo.vo.RoleInfoVO;
import com.xinkai.admin.boot.pojo.vo.RoleMenuPermVO;
import com.xinkai.admin.boot.pojo.vo.RoleOptionsVO;
import com.xinkai.admin.boot.service.RoleService;
import com.xinkai.common.core.result.PageResult;
import com.xinkai.common.core.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @className: RoleController
 * @description: 控制层
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Api(tags = "角色表对象功能接口")
@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    /**
     * 获取角色列表
     *
     * @param roleOptionsQuery 角色选择查询
     * @return {@link PageResult}<{@link RoleOptionsVO}>
     */
    @GetMapping("/list")
    public PageResult<RoleOptionsVO> list(RoleOptionsQuery roleOptionsQuery) {
        return PageResult.success(roleService.list(roleOptionsQuery));
    }

    /**
     * 页面
     *
     * @param roleListQuery 角色列表查询
     * @return {@link PageResult}<{@link RoleInfoVO}>
     */
    @GetMapping("/pages")
    public PageResult<RoleInfoVO> pages(RoleListQuery roleListQuery) {
        return PageResult.success(roleService.pages(roleListQuery));
    }

    /**
     * 细部
     *
     * @param roleId 角色ID
     * @return {@link Result}<{@link RoleInfoVO}>
     */
    @ApiOperation(value = "角色详情")
    @GetMapping("/{roleId}")
    public Result<RoleInfoVO> detail(@ApiParam("角色ID") @PathVariable Long roleId) {
        return Result.success(roleService.detail(roleId));
    }

    /**
     * 修改角色
     *
     * @param roleDTO 角色Dto
     * @return {@link Result}<{@link Boolean}>
     */
    @ApiOperation(value = "修改角色")
    @PutMapping(value = "/{id}")
    public Result<Boolean> update(@Valid @RequestBody RoleDTO roleDTO) {
        return Result.judge(roleService.update(roleDTO));
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/{ids}")
    public Result<Boolean> deleteRoles(@ApiParam("删除角色，多个以英文逗号(,)分割") @PathVariable String ids) {
        return Result.judge(roleService.delete(ids));
    }

    /**
     * 更新角色资源
     *
     * @param roleId          角色ID
     * @param roleMenuPermDTO 角色菜单权限dto
     * @return {@link Result}<{@link RoleMenuPermVO}>
     */
    @ApiOperation(value = "获取角色的资源ID集合")
    @PutMapping("/{roleId}/resources")
    public Result<RoleMenuPermVO> updateRoleResource(@PathVariable Long roleId, @RequestBody RoleMenuPermDTO roleMenuPermDTO) {
        return Result.judge(roleService.updateRoleResource(roleId, roleMenuPermDTO));
    }

    /**
     * 获取角色资源
     *
     * @param roleId 角色ID
     * @return {@link Result}<{@link RoleMenuPermVO}>
     */
    @ApiOperation(value = "获取角色的资源ID集合", notes = "资源包括菜单和权限ID")
    @GetMapping("/{roleId}/resources")
    public Result<RoleMenuPermVO> getRoleResources(@ApiParam("角色ID") @PathVariable Long roleId) {
        RoleMenuPermVO resourceIds = roleService.getRoleResources(roleId);
        return Result.success(resourceIds);
    }
}

