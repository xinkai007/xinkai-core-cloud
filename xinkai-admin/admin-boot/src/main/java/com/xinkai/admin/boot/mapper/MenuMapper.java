package com.xinkai.admin.boot.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.xinkai.admin.boot.pojo.entity.MenuEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: MenuMapper
 * @description: 菜单管理
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Mapper
public interface MenuMapper extends MPJBaseMapper<MenuEntity> {
}