package com.xinkai.admin.boot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinkai.admin.boot.pojo.query.DictTypePageQuery;
import com.xinkai.admin.boot.pojo.vo.DictTypePageVO;

/**
 * @className: DictTypeService
 * @description: 服务接口
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
public interface DictTypeService {
    /**
     * 字典类型分页列表
     *
     * @param queryParams 分页查询对象
     * @return
     */
    Page<DictTypePageVO> listDictTypePages(DictTypePageQuery queryParams);
}