package com.xinkai.admin.boot.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.xinkai.admin.boot.pojo.entity.AuthClientEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: AuthClientMapper
 * @description: 鉴权客户端表
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Mapper
public interface AuthClientMapper extends MPJBaseMapper<AuthClientEntity> {
}