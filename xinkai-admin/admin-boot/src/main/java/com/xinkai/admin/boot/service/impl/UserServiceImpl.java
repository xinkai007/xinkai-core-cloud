package com.xinkai.admin.boot.service.impl;

import cn.hutool.core.lang.Assert;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.xinkai.admin.api.dto.UserAuthDTO;
import com.xinkai.admin.boot.mapper.UserMapper;
import com.xinkai.admin.boot.pojo.entity.RoleEntity;
import com.xinkai.admin.boot.pojo.entity.UserEntity;
import com.xinkai.admin.boot.pojo.entity.UserRoleEntity;
import com.xinkai.admin.boot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.xinkai.common.core.result.ResultCode.USER_NOT_EXIST;

/**
 * @className: UserServiceImpl
 * @description: 服务实现类
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    /**
     * 根据用户名获取用户信息
     *
     * @param userName 用户名
     * @return {@link UserAuthDTO}
     */
    @Override
    public UserAuthDTO getUserByUserName(String userName) {
        UserAuthDTO userAuthDTO = userMapper.selectJoinOne(UserAuthDTO.class,
                new MPJLambdaWrapper<UserEntity>()
                        .selectAs(UserEntity::getId, UserAuthDTO::getUserId)
                        .selectAs(UserEntity::getUserName, UserAuthDTO::getUserName)
                        .selectAs(UserEntity::getNickName, UserAuthDTO::getNickName)
                        .selectAs(UserEntity::getPassword, UserAuthDTO::getPassword)
                        .selectAs(UserEntity::getStatus, UserAuthDTO::getStatus)
                        .selectAs(UserEntity::getDeptId, UserAuthDTO::getDeptId)
                        .selectAs(RoleEntity::getCode, UserAuthDTO::getRoles)
                        .leftJoin(UserRoleEntity.class, UserRoleEntity::getUserId, UserEntity::getId)
                        .leftJoin(RoleEntity.class, RoleEntity::getId, UserRoleEntity::getRoleId)
                        .eq(UserEntity::getUserName, userName)
                        .eq(UserEntity::getIsDelete, 0));
        Assert.isTrue(userAuthDTO != null, USER_NOT_EXIST.getMsg());
        return userAuthDTO;
    }
}