package com.xinkai.admin.boot.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinkai.admin.boot.converter.DictTypeConverter;
import com.xinkai.admin.boot.mapper.DictTypeMapper;
import com.xinkai.admin.boot.pojo.entity.DictItemEntity;
import com.xinkai.admin.boot.pojo.entity.DictTypeEntity;
import com.xinkai.admin.boot.pojo.from.DictTypeForm;
import com.xinkai.admin.boot.pojo.query.DictTypePageQuery;
import com.xinkai.admin.boot.pojo.vo.DictTypePageVO;
import com.xinkai.admin.boot.service.DictItemService;
import com.xinkai.admin.boot.service.DictTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: DictTypeServiceImpl
 * @description: 服务实现类
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictTypeEntity> implements DictTypeService {
    private final DictTypeConverter dictTypeConverter;
    private final DictItemService dictItemService;

    /**
     * 字典类型分页列表
     *
     * @param queryParams 分页查询对象
     * @return {@link Page}<{@link DictTypePageVO}>
     */
    @Override
    public Page<DictTypePageVO> listDictTypePages(DictTypePageQuery queryParams) {
        // 查询参数
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        String keywords = queryParams.getKeywords();

        // 查询数据
        Page<DictTypeEntity> dictTypePage = this.page(new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<DictTypeEntity>()
                        .select(DictTypeEntity::getId, DictTypeEntity::getName, DictTypeEntity::getCode, DictTypeEntity::getStatus)
                        .like(StrUtil.isNotBlank(keywords), DictTypeEntity::getName, keywords)
                        .or()
                        .like(StrUtil.isNotBlank(keywords), DictTypeEntity::getCode, keywords)
        );

        // 实体转换
        return dictTypeConverter.entity2Page(dictTypePage);
    }


    /**
     * 获取字典类型表单详情
     *
     * @param id 字典类型ID
     * @return {@link DictTypeForm}
     */
    @Override
    public DictTypeForm getDictTypeFormData(Long id) {
        // 获取entity
        DictTypeEntity entity = this.getOne(new LambdaQueryWrapper<DictTypeEntity>()
                .eq(DictTypeEntity::getId, id)
                .select(DictTypeEntity::getId,
                        DictTypeEntity::getName,
                        DictTypeEntity::getCode,
                        DictTypeEntity::getStatus,
                        DictTypeEntity::getRemark
                ));
        Assert.isTrue(entity != null, "字典类型不存在");

        // 实体转换
        return dictTypeConverter.entity2Form(entity);
    }

    /**
     * 新增字典类型
     *
     * @param dictTypeForm 法令类型形式
     * @return boolean
     */
    @Override
    public boolean saveDictType(DictTypeForm dictTypeForm) {
        // 实体对象转换 form->entity
        DictTypeEntity entity = dictTypeConverter.form2Entity(dictTypeForm);
        // 持久化
        return this.save(entity);
    }


    /**
     * 修改字典类型
     *
     * @param id           字典类型ID
     * @param dictTypeForm 字典类型表单
     * @return boolean
     */
    @Override
    public boolean updateDictType(Long id, DictTypeForm dictTypeForm) {
        // 获取字典类型
        DictTypeEntity sysDictType = this.getById(id);
        Assert.isTrue(sysDictType != null, "字典类型不存在");

        DictTypeEntity entity = dictTypeConverter.form2Entity(dictTypeForm);
        boolean result = this.updateById(entity);
        if (result) {
            // 字典类型code变化，同步修改字典项的类型code
            String oldCode = sysDictType.getCode();
            String newCode = dictTypeForm.getCode();
            if (!StrUtil.equals(oldCode, newCode)) {
                dictItemService.update(new LambdaUpdateWrapper<DictItemEntity>()
                        .eq(DictItemEntity::getTypeCode, oldCode)
                        .set(DictItemEntity::getTypeCode, newCode)
                );
            }
        }
        return result;
    }

    /**
     * 删除字典类型
     *
     * @param idsStr 字典类型ID，多个以英文逗号(,)分割
     * @return boolean
     */
    @Override
    @Transactional
    public boolean deleteDictTypes(String idsStr) {

        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除数据为空");
        //
        List<Long> ids = Arrays.stream(idsStr.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        // 删除字典项
        List<String> dictTypeCodes = this.list(new LambdaQueryWrapper<DictTypeEntity>()
                        .in(DictTypeEntity::getId, ids)
                        .select(DictTypeEntity::getCode))
                .stream().map(DictTypeEntity::getCode)
                .collect(Collectors.toList()
                );
        if (CollectionUtil.isNotEmpty(dictTypeCodes)) {
            dictItemService.remove(new LambdaQueryWrapper<DictItemEntity>().in(DictItemEntity::getTypeCode, dictTypeCodes));
        }
        // 删除字典类型
        return this.removeByIds(ids);
    }
}