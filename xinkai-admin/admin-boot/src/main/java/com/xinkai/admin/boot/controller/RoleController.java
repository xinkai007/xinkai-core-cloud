package com.xinkai.admin.boot.controller;

import com.xinkai.admin.boot.pojo.query.RoleOptionsQuery;
import com.xinkai.admin.boot.pojo.vo.RoleOptionsVO;
import com.xinkai.admin.boot.service.RoleService;
import com.xinkai.common.core.result.PageResult;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}