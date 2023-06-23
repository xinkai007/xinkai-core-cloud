package com.xinkai.admin.boot.controller;

import com.xinkai.admin.boot.service.DictItemService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: DictItemController
 * @description: 控制层
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Api(tags = "字典数据表对象功能接口")
@RestController
@RequestMapping("/api/v1/dictItem")
@RequiredArgsConstructor
public class DictItemController {
    private final DictItemService dictItemService;
}