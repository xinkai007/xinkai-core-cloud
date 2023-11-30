package com.xinkai.admin.boot.controller;

import cn.hutool.core.lang.tree.Tree;
import com.xinkai.admin.boot.pojo.vo.MenuPermVO;
import com.xinkai.admin.boot.service.MenuService;
import com.xinkai.common.core.result.Result;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}