package com.xinkai.admin.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinkai.admin.boot.pojo.entity.DictTypeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: DictTypeMapper
 * @description: 字典类型表
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Mapper
public interface DictTypeMapper extends BaseMapper<DictTypeEntity> {
}