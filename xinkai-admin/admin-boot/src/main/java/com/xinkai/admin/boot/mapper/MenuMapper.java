package com.xinkai.admin.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface MenuMapper extends BaseMapper<MenuEntity> {
}