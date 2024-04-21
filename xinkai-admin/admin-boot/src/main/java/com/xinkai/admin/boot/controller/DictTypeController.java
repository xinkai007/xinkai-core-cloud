package com.xinkai.admin.boot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinkai.admin.boot.pojo.query.DictTypePageQuery;
import com.xinkai.admin.boot.pojo.vo.DictTypePageVO;
import com.xinkai.admin.boot.service.DictTypeService;
import com.xinkai.common.core.result.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}