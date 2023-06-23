package com.xinkai.admin.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinkai.admin.boot.pojo.entity.RoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: RoleMenuMapper
 * @description: 角色和菜单关联表
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenuEntity> {
}