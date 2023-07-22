package com.xinkai.admin.boot.controller;

import cn.hutool.core.lang.tree.Tree;
import com.xinkai.admin.boot.pojo.dto.DeptInfoDTO;
import com.xinkai.admin.boot.pojo.query.DeptListQuery;
import com.xinkai.admin.boot.pojo.vo.DeptListVO;
import com.xinkai.admin.boot.service.DeptService;
import com.xinkai.common.core.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 搜索
     * 部门搜索
     *
     * @param deptListQuery 部门列表查询
     * @return {@link Result}<{@link List}<{@link DeptListVO}>>
     */
    @GetMapping("/search")
    public Result<List<DeptListVO>> search(DeptListQuery deptListQuery) {
        return Result.success(deptService.search(deptListQuery));
    }

    /**
     * 添加
     * 添加部门
     *
     * @param deptInfoDTO 部门信息dto
     * @return {@link Result}<{@link Boolean}>
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody DeptInfoDTO deptInfoDTO) {
        return Result.judge(deptService.add(deptInfoDTO));
    }

    /**
     * 细节
     * 部门详情
     *
     * @param id id
     * @return {@link Result}<{@link DeptInfoDTO}>
     */
    @GetMapping("/detail/{id}")
    public Result<DeptInfoDTO> detail(@PathVariable Long id) {
        return Result.success(deptService.detail(id));
    }

    /**
     * 修改部门信息
     *
     * @param deptInfoDTO 部门信息dto
     * @return {@link Result}<{@link Boolean}>
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody DeptInfoDTO deptInfoDTO) {
        return Result.success(deptService.update(deptInfoDTO));
    }

    @ApiOperation(value = "删除部门信息")
    @DeleteMapping("/delete/{ids}")
    public Result<Boolean> delete(@ApiParam("部门ID多个以英文逗号分割") @PathVariable String ids) {
        return Result.judge(deptService.delete(ids));
    }
}