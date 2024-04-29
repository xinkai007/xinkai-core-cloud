package com.xinkai.admin.boot.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinkai.admin.boot.converter.DictItemConverter;
import com.xinkai.admin.boot.mapper.DictItemMapper;
import com.xinkai.admin.boot.pojo.entity.DictItemEntity;
import com.xinkai.admin.boot.pojo.from.DictItemForm;
import com.xinkai.admin.boot.pojo.query.DictItemPageQuery;
import com.xinkai.admin.boot.pojo.vo.DictItemPageVO;
import com.xinkai.admin.boot.service.DictItemService;
import com.xinkai.common.mybatis.base.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @className: DictItemServiceImpl
 * @description: 服务实现类
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DictItemServiceImpl extends ServiceImpl<DictItemMapper, DictItemEntity> implements DictItemService {
    private final DictItemConverter dictItemConverter;

    /**
     * 根据字典类型编码获取字典数据项
     *
     * @param typeCode 字典类型编码
     * @return {@link List}<{@link Option}<{@link String}>>
     */
    @Override
    public List<Option<String>> listDictItemsByTypeCode(String typeCode) {
        // 数据字典项
        List<DictItemEntity> dictItems = this.list(new LambdaQueryWrapper<DictItemEntity>()
                .eq(DictItemEntity::getTypeCode, typeCode)
                .select(DictItemEntity::getValue, DictItemEntity::getName)
        );
        // 转换下拉数据
        List<Option<String>> options = Optional.ofNullable(dictItems).orElse(new ArrayList<>()).stream()
                .map(dictItem -> new Option<String>(dictItem.getValue(), dictItem.getName()))
                .collect(Collectors.toList());
        log.info("DictItemServiceImpl.listDictItemsByTypeCode options:{}", options);
        return options;
    }


    /**
     * 字典数据项分页列表
     *
     * @param queryParams 查询参数
     * @return {@link Page}<{@link DictItemPageVO}>
     */
    @Override
    public Page<DictItemPageVO> listDictItemPages(DictItemPageQuery queryParams) {
        // 查询参数
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        String keywords = queryParams.getKeywords();
        String typeCode = queryParams.getTypeCode();

        // 查询数据
        Page<DictItemEntity> dictItemPage = this.page(
                new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<DictItemEntity>()
                        .like(StrUtil.isNotBlank(keywords), DictItemEntity::getName, keywords)
                        .eq(StrUtil.isNotBlank(typeCode), DictItemEntity::getTypeCode, typeCode)
                        .select(DictItemEntity::getId, DictItemEntity::getName, DictItemEntity::getValue, DictItemEntity::getStatus)
        );

        // 实体转换
        return dictItemConverter.entity2Page(dictItemPage);
    }

    /**
     * 字典数据项表单详情
     *
     * @param id 字典数据项ID
     * @return {@link DictItemForm}
     */
    @Override
    public DictItemForm getDictItemFormData(Long id) {
        // 获取entity
        DictItemEntity entity = this.getOne(new LambdaQueryWrapper<DictItemEntity>()
                .eq(DictItemEntity::getId, id)
                .select(DictItemEntity::getId,
                        DictItemEntity::getTypeCode,
                        DictItemEntity::getName,
                        DictItemEntity::getValue,
                        DictItemEntity::getStatus,
                        DictItemEntity::getSort,
                        DictItemEntity::getRemark
                ));
        Assert.isTrue(entity != null, "字典数据项不存在");
        // 实体转换
        return dictItemConverter.entity2Form(entity);
    }

    /**
     * 新增字典数据项
     *
     * @param dictItemForm 字典数据项表单
     * @return boolean
     */
    @Override
    public boolean saveDictItem(DictItemForm dictItemForm) {
        // 实体对象转换 form->entity
        DictItemEntity entity = dictItemConverter.form2Entity(dictItemForm);
        // 持久化
        return this.save(entity);
    }

    /**
     * 修改字典数据项
     *
     * @param id           字典数据项ID
     * @param dictItemForm 字典数据项表单
     * @return boolean
     */
    @Override
    public boolean updateDictItem(Long id, DictItemForm dictItemForm) {
        DictItemEntity entity = dictItemConverter.form2Entity(dictItemForm);
        return this.updateById(entity);
    }

    /**
     * 删除字典数据项
     *
     * @param idsStr 字典数据项ID，多个以英文逗号(,)分割
     * @return boolean
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean deleteDictItems(String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除数据为空");
        List<Long> ids = Arrays.stream(idsStr.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        // 删除字典数据项
        return this.removeByIds(ids);
    }
}