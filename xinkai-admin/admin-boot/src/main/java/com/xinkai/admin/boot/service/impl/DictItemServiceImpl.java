package com.xinkai.admin.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinkai.admin.boot.mapper.DictItemMapper;
import com.xinkai.admin.boot.pojo.entity.DictItemEntity;
import com.xinkai.admin.boot.service.DictItemService;
import com.xinkai.common.mybatis.base.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private final DictItemMapper dictItemMapper;

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
}