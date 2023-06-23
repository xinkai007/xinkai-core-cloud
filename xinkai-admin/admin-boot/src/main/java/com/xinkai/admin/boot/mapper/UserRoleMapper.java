package com.xinkai.admin.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinkai.admin.boot.pojo.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: UserRoleMapper
 * @description: 用户和角色关联表
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {
}