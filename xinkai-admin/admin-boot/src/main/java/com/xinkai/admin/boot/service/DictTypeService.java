package com.xinkai.admin.boot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinkai.admin.boot.pojo.from.DictTypeForm;
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
     * @return {@link Page}<{@link DictTypePageVO}>
     */
    Page<DictTypePageVO> listDictTypePages(DictTypePageQuery queryParams);


    /**
     * 获取字典类型表单详情
     *
     * @param id 字典类型ID
     * @return {@link DictTypeForm}
     */
    DictTypeForm getDictTypeFormData(Long id);


    /**
     * 新增字典类型
     *
     * @param dictTypeForm 字典类型表单
     * @return boolean
     */
    boolean saveDictType(DictTypeForm dictTypeForm);


    /**
     * 修改字典类型
     *
     * @param dictTypeForm 字典类型表单
     * @param id           ID
     * @return boolean
     */
    boolean updateDictType(Long id, DictTypeForm dictTypeForm);

    /**
     * 删除字典类型
     *
     * @param idsStr 字典类型ID，多个以英文逗号(,)分割
     * @return boolean
     */
    boolean deleteDictTypes(String idsStr);
}