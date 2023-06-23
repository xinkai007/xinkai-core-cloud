package com.xinkai.admin.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinkai.admin.boot.pojo.entity.DictItemEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: DictItemMapper
 * @description: 字典数据表
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Mapper
public interface DictItemMapper extends BaseMapper<DictItemEntity> {
}