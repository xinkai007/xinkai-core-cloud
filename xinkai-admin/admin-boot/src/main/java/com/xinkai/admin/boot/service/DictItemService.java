package com.xinkai.admin.boot.service;

import com.xinkai.common.mybatis.base.Option;

import java.util.List;

/**
 * @className: DictItemService
 * @description: 服务接口
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
public interface DictItemService {

    List<Option<String>> listDictItemsByTypeCode(String typeCode);
}