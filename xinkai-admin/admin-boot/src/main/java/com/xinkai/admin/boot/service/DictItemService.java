package com.xinkai.admin.boot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinkai.admin.boot.pojo.entity.DictItemEntity;
import com.xinkai.admin.boot.pojo.from.DictItemForm;
import com.xinkai.admin.boot.pojo.query.DictItemPageQuery;
import com.xinkai.admin.boot.pojo.vo.DictItemPageVO;
import com.xinkai.common.mybatis.base.Option;

import java.util.List;

/**
 * @className: DictItemService
 * @description: 服务接口
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
public interface DictItemService extends IService<DictItemEntity> {

    List<Option<String>> listDictItemsByTypeCode(String typeCode);


    /**
     * 字典数据项分页列表
     *
     * @param queryParams 查询参数
     * @return {@link Page}<{@link DictItemPageVO}>
     */
    Page<DictItemPageVO> listDictItemPages(DictItemPageQuery queryParams);

    /**
     * 字典数据项表单详情
     *
     * @param id 字典数据项ID
     * @return {@link DictItemForm}
     */
    DictItemForm getDictItemFormData(Long id);

    /**
     * 新增字典数据项
     *
     * @param dictItemForm 字典数据项表单
     * @return boolean
     */
    boolean saveDictItem(DictItemForm dictItemForm);

    /**
     * 修改字典数据项
     *
     * @param id           字典数据项ID
     * @param dictItemForm 字典数据项表单
     * @return boolean
     */
    boolean updateDictItem(Long id, DictItemForm dictItemForm);

    /**
     * 删除字典数据项
     *
     * @param idsStr 字典数据项ID，多个以英文逗号(,)分割
     * @return boolean
     */
    boolean deleteDictItems(String idsStr);
}