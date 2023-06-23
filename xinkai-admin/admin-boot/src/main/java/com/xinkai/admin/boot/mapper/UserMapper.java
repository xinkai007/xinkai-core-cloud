package com.xinkai.admin.boot.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.xinkai.admin.boot.pojo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: UserMapper
 * @description: 用户信息表
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Mapper
public interface UserMapper extends MPJBaseMapper<UserEntity> {
}