package com.xinkai.admin.boot.service.impl;

import cn.hutool.core.lang.Assert;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.xinkai.admin.api.dto.UserAuthDTO;
import com.xinkai.admin.boot.mapper.UserMapper;
import com.xinkai.admin.boot.pojo.entity.RoleEntity;
import com.xinkai.admin.boot.pojo.entity.UserEntity;
import com.xinkai.admin.boot.pojo.entity.UserRoleEntity;
import com.xinkai.admin.boot.pojo.vo.UserInfoVO;
import com.xinkai.admin.boot.service.UserService;
import com.xinkai.common.web.utils.UserUtils;
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
    public UserAuthDTO userInfo(String userName) {
        UserAuthDTO userAuthDTO = userMapper.selectJoinOne(UserAuthDTO.class,
                new MPJLambdaWrapper<UserEntity>()
                        .selectAs(UserEntity::getId, UserAuthDTO::getUserId)
                        .selectAs(UserEntity::getUserName, UserAuthDTO::getUserName)
                        .selectAs(UserEntity::getNickName, UserAuthDTO::getNickName)
                        .selectAs(UserEntity::getPassword, UserAuthDTO::getPassword)
                        .selectAs(UserEntity::getStatus, UserAuthDTO::getStatus)
                        .selectAs(UserEntity::getDeptId, UserAuthDTO::getDeptId)
                        .selectCollection(UserAuthDTO::getRoles, map -> map
                                .result(RoleEntity::getCode))
                        .leftJoin(UserRoleEntity.class, UserRoleEntity::getUserId, UserEntity::getId)
                        .leftJoin(RoleEntity.class, RoleEntity::getId, UserRoleEntity::getRoleId)
                        .eq(UserEntity::getUserName, userName)
                        .eq(UserEntity::getIsDelete, 0));
        Assert.isTrue(userAuthDTO != null, USER_NOT_EXIST.getMsg());
        return userAuthDTO;
    }

    /**
     * 获取用户信息
     *
     * @return {@link UserInfoVO}
     */
    @Override
    public UserInfoVO loginUserInfo() {
        return userMapper.selectJoinOne(UserInfoVO.class,
                new MPJLambdaWrapper<UserEntity>()
                        .selectAs(UserEntity::getId, UserInfoVO::getUserId)
                        .selectAs(UserEntity::getNickName, UserInfoVO::getNickName)
                        .selectAs(UserEntity::getAvatar, UserInfoVO::getAvatar)
                        .selectCollection(UserInfoVO::getRoles, map -> map
                                .result(RoleEntity::getCode))
                        .leftJoin(UserRoleEntity.class, UserRoleEntity::getUserId, UserEntity::getId)
                        .leftJoin(RoleEntity.class, RoleEntity::getId, UserRoleEntity::getRoleId)
                        .eq(UserEntity::getId, UserUtils.getUserId())
                        .eq(UserEntity::getIsDelete, 0)
        );

    }
}