package com.xinkai.admin.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinkai.admin.boot.pojo.entity.RolePermissionEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: RolePermissionMapper
 * @description: 角色权限表
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermissionEntity> {
}