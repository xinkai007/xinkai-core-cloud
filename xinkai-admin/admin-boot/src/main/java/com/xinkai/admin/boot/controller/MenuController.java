package com.xinkai.admin.boot.controller;

import cn.hutool.core.lang.tree.Tree;
import com.xinkai.admin.boot.pojo.entity.MenuEntity;
import com.xinkai.admin.boot.pojo.vo.MenuPermVO;
import com.xinkai.admin.boot.pojo.vo.MenuVO;
import com.xinkai.admin.boot.service.MenuService;
import com.xinkai.admin.boot.service.PermissionService;
import com.xinkai.common.core.result.Result;
import com.xinkai.common.mybatis.base.Option;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: MenuController
 * @description: 控制层
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Api(tags = "菜单管理对象功能接口")
@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
    private final PermissionService permissionService;

    @GetMapping
    @ApiOperation(value = "菜单列表")
    public Result<List<MenuVO>> list(@ApiParam(value = "菜单名称", type = "query") String name) {
        return Result.success(menuService.list(name));
    }

    /**
     * 获取路线
     * 获取用户导航栏菜单信息
     *
     * @return {@link Result}<{@link List}<{@link Tree}<{@link Long}>>>
     */
    @GetMapping("/routes")
    public Result<List<Tree<Long>>> routes() {
        return Result.success(menuService.routes());
    }

    /**
     * 资源
     *
     * @return {@link Result}<{@link MenuPermVO}>
     */
    @GetMapping("/resources")
    public Result<MenuPermVO> resources() {
        return Result.success(menuService.getMenuResources());
    }

    /**
     * 获取菜单选项
     *
     * @return {@link Result}<{@link List}<{@link Option}<{@link Long}>>>
     */
    @GetMapping("/options")
    @ApiOperation(value = "菜单列表")
    public Result<List<Option<Long>>> options() {
        return Result.success(menuService.getMenuOptions());
    }

    /**
     * 添加菜单
     *
     * @param menuEntity 菜单实体
     * @return {@link Result}<{@link Boolean}>
     */
    @PostMapping
    @ApiOperation(value = "添加菜单")
    public Result<Boolean> addMenu(@RequestBody MenuEntity menuEntity) {
        return Result.judge(menuService.saveMenu(menuEntity));
    }

    /**
     * 菜单详情
     *
     * @param id ID
     * @return {@link Result}<{@link MenuEntity}>
     */
    @ApiOperation(value = "菜单详情")
    @GetMapping("/{id}")
    public Result<MenuEntity> detail(@ApiParam(value = "菜单ID") @PathVariable Long id) {
        return Result.success(menuService.getById(id));
    }

    /**
     * 修改菜单
     *
     * @param menu 菜单
     * @return {@link Result}<{@link Boolean}>
     */
    @ApiOperation(value = "修改菜单")
    @PutMapping(value = "/{id}")
    @CacheEvict(cacheNames = "system", key = "'routes'")
    public Result<Boolean> updateMenu(@RequestBody MenuEntity menu) {
        return Result.judge(menuService.saveMenu(menu));
    }

    /**
     * 删除菜单
     *
     * @param ids IDs
     * @return {@link Result}<{@link Boolean}>
     */
    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/{ids}")
    @CacheEvict(cacheNames = "system", key = "'routes'")
    public Result<Boolean> deleteMenus(@ApiParam("菜单ID，多个以英文(,)分割") @PathVariable("ids") String ids) {
        boolean result = menuService.removeByIds(Arrays.stream(ids.split(",")).map(Long::valueOf).collect(Collectors.toList()));
        if (result) {
            permissionService.refreshPermRolesRules();
        }
        return Result.judge(result);
    }
}