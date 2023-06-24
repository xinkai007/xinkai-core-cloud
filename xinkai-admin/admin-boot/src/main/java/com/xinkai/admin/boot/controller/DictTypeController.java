package com.xinkai.admin.boot.controller;

import com.xinkai.admin.boot.service.DictTypeService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
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
}