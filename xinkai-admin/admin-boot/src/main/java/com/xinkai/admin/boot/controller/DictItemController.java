package com.xinkai.admin.boot.controller;

import com.xinkai.admin.boot.service.DictItemService;
import com.xinkai.common.core.result.Result;
import com.xinkai.common.mybatis.base.Option;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}