package com.xinkai.admin.boot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinkai.admin.boot.pojo.from.DictItemForm;
import com.xinkai.admin.boot.pojo.query.DictItemPageQuery;
import com.xinkai.admin.boot.pojo.vo.DictItemPageVO;
import com.xinkai.admin.boot.service.DictItemService;
import com.xinkai.common.core.result.PageResult;
import com.xinkai.common.core.result.Result;
import com.xinkai.common.mybatis.base.Option;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @className: DictItemController
 * @description: 控制层
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Api(tags = "字典数据表对象功能接口")
@RestController
@RequestMapping("/api/v1/dict_item")
@RequiredArgsConstructor
public class DictItemController {
    private final DictItemService dictItemService;

    /**
     * 根据字典类型编码获取字典数据项
     *
     * @param typeCode 类型代码
     * @return {@link Result}<{@link List}<{@link Option}<{@link String}>>>
     */
    @ApiOperation(value = "根据字典类型编码获取字典数据项")
    @GetMapping("/select_list")
    public Result<List<Option<String>>> getDictItemsByTypeCode(@ApiParam("字典类型编码") @RequestParam String typeCode) {
        List<Option<String>> list = dictItemService.listDictItemsByTypeCode(typeCode);
        return Result.success(list);
    }


    @ApiOperation(value = "字典数据项分页列表")
    @GetMapping
    public PageResult<DictItemPageVO> listDictItemPages(DictItemPageQuery queryParams) {
        Page<DictItemPageVO> result = dictItemService.listDictItemPages(queryParams);
        return PageResult.success(result);
    }

    @ApiOperation(value = "字典数据项表单")
    @GetMapping("/{id}/form_data")
    public Result<DictItemForm> getDictItemFormData(@ApiParam("字典ID") @PathVariable Long id) {
        DictItemForm formData = dictItemService.getDictItemFormData(id);
        return Result.success(formData);
    }

    @ApiOperation(value = "新增字典数据项")
    @PostMapping
    public Result<Boolean> saveDictItem(@RequestBody DictItemForm DictItemForm) {
        boolean result = dictItemService.saveDictItem(DictItemForm);
        return Result.judge(result);
    }

    @ApiOperation(value = "修改字典数据项")
    @PutMapping("/{id}")
    public Result<Boolean> updateDictItem(@PathVariable Long id, @RequestBody DictItemForm DictItemForm) {
        boolean status = dictItemService.updateDictItem(id, DictItemForm);
        return Result.judge(status);
    }

    @ApiOperation(value = "删除字典")
    @DeleteMapping("/{ids}")
    public Result<Boolean> deleteDictItems(@ApiParam("字典ID，多个以英文逗号(,)分割") @PathVariable String ids) {
        boolean result = dictItemService.deleteDictItems(ids);
        return Result.judge(result);
    }
}