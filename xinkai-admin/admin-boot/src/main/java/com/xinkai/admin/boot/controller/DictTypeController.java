package com.xinkai.admin.boot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinkai.admin.boot.pojo.from.DictTypeForm;
import com.xinkai.admin.boot.pojo.query.DictTypePageQuery;
import com.xinkai.admin.boot.pojo.vo.DictTypePageVO;
import com.xinkai.admin.boot.service.DictTypeService;
import com.xinkai.common.core.result.PageResult;
import com.xinkai.common.core.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @className: DictTypeController
 * @description: 控制层
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Api(tags = "字典类型表对象功能接口")
@RestController
@RequestMapping("/api/v1/dict_type")
@RequiredArgsConstructor
public class DictTypeController {
    private final DictTypeService dictTypeService;

    /**
     * 列表法令类型页面
     *
     * @param queryParams 查询参数
     * @return {@link PageResult}<{@link DictTypePageVO}>
     */
    @ApiOperation(value = "字典类型分页列表")
    @GetMapping
    public PageResult<DictTypePageVO> listDictTypePages(DictTypePageQuery queryParams) {
        Page<DictTypePageVO> result = dictTypeService.listDictTypePages(queryParams);
        return PageResult.success(result);
    }


    /**
     * 字典类型表单详情
     *
     * @param id ID
     * @return {@link Result}<{@link DictTypeForm}>
     */
    @ApiOperation(value = "字典类型表单详情")
    @GetMapping("/{id}/form_data")
    public Result<DictTypeForm> getDictTypeFormData(@ApiParam("字典ID") @PathVariable Long id) {
        return Result.success(dictTypeService.getDictTypeFormData(id));
    }

    /**
     * 新增字典类型
     *
     * @param dictTypeForm 法令类型形式
     * @return {@link Result}<{@link Boolean}>
     */
    @ApiOperation(value = "新增字典类型")
    @PostMapping
    public Result<Boolean> saveDictType(@RequestBody DictTypeForm dictTypeForm) {
        boolean result = dictTypeService.saveDictType(dictTypeForm);
        return Result.judge(result);
    }

    /**
     * 修改字典类型
     *
     * @param id           ID
     * @param dictTypeForm 字典类型表单
     * @return {@link Result}<{@link Boolean}>
     */
    @ApiOperation(value = "修改字典类型")
    @PutMapping("/{id}")
    public Result<Boolean> updateDict(@PathVariable Long id, @RequestBody DictTypeForm dictTypeForm) {
        boolean status = dictTypeService.updateDictType(id, dictTypeForm);
        return Result.judge(status);
    }

    /**
     * 删除字典类型
     *
     * @param ids IDs
     * @return {@link Result}<{@link Boolean}>
     */
    @ApiOperation(value = "删除字典类型")
    @DeleteMapping("/{ids}")
    public Result<Boolean> deleteDictTypes(@ApiParam("字典类型ID，多个以英文逗号(,)分割") @PathVariable String ids) {
        boolean result = dictTypeService.deleteDictTypes(ids);
        return Result.judge(result);
    }
}