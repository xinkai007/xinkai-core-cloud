package com.xinkai.admin.boot.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.xinkai.admin.api.dto.UserAuthDTO;
import com.xinkai.admin.boot.mapper.UserMapper;
import com.xinkai.admin.boot.pojo.dto.UserUpdatePasswordDTO;
import com.xinkai.admin.boot.pojo.dto.UserUpdateStatusDTO;
import com.xinkai.admin.boot.pojo.entity.RoleEntity;
import com.xinkai.admin.boot.pojo.entity.UserEntity;
import com.xinkai.admin.boot.pojo.entity.UserRoleEntity;
import com.xinkai.admin.boot.pojo.query.UserListQuery;
import com.xinkai.admin.boot.pojo.vo.UserDetailVO;
import com.xinkai.admin.boot.pojo.vo.UserInfoVO;
import com.xinkai.admin.boot.pojo.vo.UserListVO;
import com.xinkai.admin.boot.service.UserService;
import com.xinkai.admin.boot.utils.dict.impl.UserDictConvert;
import com.xinkai.common.core.exception.SystemException;
import com.xinkai.common.core.result.ResultCode;
import com.xinkai.common.web.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
    private final UserDictConvert userDictConvert;
    private final PasswordEncoder passwordEncoder;

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

    /**
     * 用户列表查询
     *
     * @param userListQuery 用户列表查询
     * @return {@link IPage}<{@link UserListVO}>
     */
    @Override
    @SneakyThrows
    public IPage<UserListVO> pages(UserListQuery userListQuery) {
        try {
            Integer status = userListQuery.getStatus();
            Integer dept = userListQuery.getDeptId();
            String keywords = userListQuery.getKeywords();
            return userDictConvert.dictConvertPage(
                    userMapper.selectJoinPage(new Page<UserListVO>(userListQuery.getPageNum(), userListQuery.getPageSize()),
                            UserListVO.class,
                            new MPJLambdaWrapper<UserEntity>()
                                    .selectAs(UserEntity::getId, UserListVO::getId)
                                    .selectAs(UserEntity::getUserName, UserListVO::getUserName)
                                    .selectAs(UserEntity::getNickName, UserListVO::getNickName)
                                    .selectAs(UserEntity::getGender, UserListVO::getGender)
                                    .selectAs(UserEntity::getDeptId, UserListVO::getDeptId)
                                    .selectAs(UserEntity::getMobile, UserListVO::getMobile)
                                    .selectAs(UserEntity::getStatus, UserListVO::getStatus)
                                    .selectAs(UserEntity::getCreateTime, UserListVO::getCreateTime)
                                    .eq(!Objects.equals(status, null) && !Objects.equals(status, -1), UserEntity::getStatus, status)
                                    .eq(!Objects.equals(dept, null), UserEntity::getDeptId, dept)
                                    .and(CharSequenceUtil.isNotEmpty(keywords), e -> e.like(UserEntity::getUserName, keywords)
                                            .or().like(UserEntity::getMobile, keywords)
                                            .or().likeRight(UserEntity::getNickName, keywords))
                    ));
        } catch (Exception e) {
            log.error("UserServiceImpl.listQuery e:", e);
            throw new SystemException(ResultCode.SYSTEM_EXECUTION_ERROR);
        }
    }

    /**
     * 用户详细信息
     *
     * @param id id
     * @return {@link UserDetailVO}
     */
    @Override
    @SneakyThrows
    public UserDetailVO detail(Long id) {
        try {
            new UserEntity().selectById(id);
            return null;
        } catch (Exception e) {
            log.error("UserServiceImpl.detail e:", e);
            throw new SystemException(ResultCode.SYSTEM_EXECUTION_ERROR);
        }
    }

    /**
     * 修改用户状态
     *
     * @param userUpdateStatusDTO 用户更新状态dto
     * @return {@link Boolean}
     */
    @Override
    @SneakyThrows
    public Boolean updateStatus(UserUpdateStatusDTO userUpdateStatusDTO) {
        try {
            return new UserEntity()
                    .setId(userUpdateStatusDTO.getId())
                    .setStatus(userUpdateStatusDTO.getStatus())
                    .updateById();
        } catch (Exception e) {
            log.error("UserServiceImpl.updateStatus e:", e);
            throw new SystemException(ResultCode.SYSTEM_EXECUTION_ERROR);
        }
    }

    /**
     * 修改用户密码
     *
     * @param userUpdatePasswordDTO 用户更新密码dto
     * @return {@link Boolean}
     */
    @Override
    @SneakyThrows
    public Boolean updatePassword(UserUpdatePasswordDTO userUpdatePasswordDTO) {
        try {
            return new UserEntity()
                    .setId(userUpdatePasswordDTO.getId())
                    .setPassword(passwordEncoder.encode(userUpdatePasswordDTO.getPassword()))
                    .updateById();
        } catch (Exception e) {
            log.error("UserServiceImpl.updateStatus e:", e);
            throw new SystemException(ResultCode.SYSTEM_EXECUTION_ERROR);
        }
    }
}