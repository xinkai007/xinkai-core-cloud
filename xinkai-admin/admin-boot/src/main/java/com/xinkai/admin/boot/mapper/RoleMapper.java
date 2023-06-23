package com.xinkai.admin.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinkai.admin.boot.pojo.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: RoleMapper
 * @description: 角色表
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {
}