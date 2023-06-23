package com.xinkai.admin.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinkai.admin.boot.pojo.entity.DeptEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: DeptMapper
 * @description: 部门表
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Mapper
public interface DeptMapper extends BaseMapper<DeptEntity> {
}