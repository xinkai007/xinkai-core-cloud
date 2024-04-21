package com.xinkai.admin.boot.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinkai.admin.boot.converter.DictTypeConverter;
import com.xinkai.admin.boot.mapper.DictTypeMapper;
import com.xinkai.admin.boot.pojo.entity.DictTypeEntity;
import com.xinkai.admin.boot.pojo.query.DictTypePageQuery;
import com.xinkai.admin.boot.pojo.vo.DictTypePageVO;
import com.xinkai.admin.boot.service.DictTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    private final DictTypeMapper dictTypeMapper;
    private final DictTypeConverter dictTypeConverter;

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
}