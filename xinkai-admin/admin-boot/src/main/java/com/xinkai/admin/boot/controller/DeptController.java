package com.xinkai.admin.boot.controller;

import cn.hutool.core.lang.tree.Tree;
import com.xinkai.admin.boot.service.DeptService;
import com.xinkai.common.core.result.Result;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: DeptController
 * @description: 控制层
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Api(tags = "部门表对象功能接口")
@RestController
@RequestMapping("/api/v1/dept")
@RequiredArgsConstructor
public class DeptController {
    private final DeptService deptService;

    /**
     * 查询部门列表
     *
     * @return {@link Result}<{@link List}<{@link Tree}<{@link Long}>>>
     */
    @GetMapping("/list")
    public Result<List<Tree<Long>>> list() {
        return Result.success(deptService.list());
    }
}