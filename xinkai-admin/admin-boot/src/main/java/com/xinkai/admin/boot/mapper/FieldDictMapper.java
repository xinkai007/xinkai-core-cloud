package com.xinkai.admin.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinkai.admin.boot.pojo.entity.FieldDictEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: FieldDictMapper
 * @description: 栏位词典表
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Mapper
public interface FieldDictMapper extends BaseMapper<FieldDictEntity> {
}