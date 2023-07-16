package com.xinkai.admin.boot.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.Db;
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
import com.xinkai.common.core.constant.GlobalConstants;
import com.xinkai.common.core.exception.SystemException;
import com.xinkai.common.core.result.ResultCode;
import com.xinkai.common.web.exception.BusinessException;
import com.xinkai.common.web.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author xinkai
 * @className com.xinkai.admin.boot.service.impl.UserServiceImpl
 * @description 用户服务impl
 * @email xinkai8011@gmail.com
 * @date 2023/07/13
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
        Assert.isTrue(userAuthDTO != null, ResultCode.USER_NOT_EXIST.getMsg());
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
                    userMapper.selectJoinPage(new Page<>(userListQuery.getPageNum(), userListQuery.getPageSize()),
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
            UserDetailVO userDetailVO = userMapper.selectJoinOne(UserDetailVO.class,
                    new MPJLambdaWrapper<UserEntity>()
                            .selectAs(UserEntity::getId, UserDetailVO::getId)
                            .selectAs(UserEntity::getUserName, UserDetailVO::getUserName)
                            .selectAs(UserEntity::getNickName, UserDetailVO::getNickName)
                            .selectAs(UserEntity::getDeptId, UserDetailVO::getDeptId)
                            .selectAs(UserEntity::getMobile, UserDetailVO::getMobile)
                            .selectAs(UserEntity::getEmail, UserDetailVO::getEmail)
                            .selectAs(UserEntity::getStatus, UserDetailVO::getStatus)
                            .selectAs(UserEntity::getGender, UserDetailVO::getGender)
                            .selectCollection(UserDetailVO::getRoleIds, map -> map.result(UserRoleEntity::getRoleId))
                            .innerJoin(UserRoleEntity.class, UserRoleEntity::getUserId, UserEntity::getId)
                            .eq(UserRoleEntity::getIsDelete, GlobalConstants.STATUS_NO)
                            .eq(UserEntity::getId, id)
            );
            Assert.notNull(userDetailVO, () -> new BusinessException(ResultCode.USER_NOT_EXIST));
            return userDetailVO;
        } catch (BusinessException e) {
            throw new BusinessException(e.getResultCode());
        } catch (Exception e) {
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

    /**
     * 修改用户信息
     *
     * @param userDetailVO 用户详细签证官
     * @return {@link Boolean}
     */
    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public Boolean update(UserDetailVO userDetailVO) {
        try {
            Long userId = userDetailVO.getId();
            boolean userUpdate = new UserEntity()
                    .setId(userId)
                    .setUserName(userDetailVO.getUserName())
                    .setNickName(userDetailVO.getNickName())
                    .setDeptId(userDetailVO.getDeptId())
                    .setMobile(userDetailVO.getMobile())
                    .setEmail(userDetailVO.getEmail())
                    .setStatus(userDetailVO.getStatus())
                    .setGender(userDetailVO.getGender())
                    .updateById();
            Assert.isTrue(userUpdate, () -> new BusinessException(ResultCode.USER_NOT_EXIST));
            return updateUserRole(userDetailVO, userId);
        } catch (BusinessException e) {
            throw new BusinessException(e.getResultCode());
        } catch (Exception e) {
            throw new SystemException(ResultCode.SYSTEM_EXECUTION_ERROR);
        }
    }

    /**
     * 添加用户
     *
     * @param userDetailVO 用户详细签证官
     * @return {@link Boolean}
     */
    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(UserDetailVO userDetailVO) {
        try {
            String userName = userDetailVO.getUserName();
            Assert.isNull(new UserEntity().selectOne(new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getUserName, userName)),
                    () -> new BusinessException(ResultCode.USER_NAME_REPEAT));
            UserEntity userEntity = new UserEntity()
                    .setUserName(userName)
                    .setNickName(userDetailVO.getNickName())
                    .setDeptId(userDetailVO.getDeptId())
                    .setMobile(userDetailVO.getMobile())
                    .setEmail(userDetailVO.getEmail())
                    .setStatus(userDetailVO.getStatus())
                    .setCreateTime(new Date())
                    .setPassword(passwordEncoder.encode(userName + GlobalConstants.DEFAULT_PASSWORD))
                    .setGender(userDetailVO.getGender());
            boolean insert = userEntity.insert();
            Assert.isTrue(insert, () -> new BusinessException(ResultCode.USER_NOT_EXIST));
            Long userId = userEntity.getId();
            return updateUserRole(userDetailVO, userId);
        } catch (BusinessException e) {
            throw new BusinessException(e.getResultCode());
        } catch (Exception e) {
            throw new SystemException(ResultCode.SYSTEM_EXECUTION_ERROR);
        }
    }

    /**
     * 更新用户角色
     *
     * @param userDetailVO 用户详细签证官
     * @param userId       用户id
     * @return {@link Boolean}
     */
    private Boolean updateUserRole(UserDetailVO userDetailVO, Long userId) {
        new UserRoleEntity().delete(new LambdaUpdateWrapper<UserRoleEntity>().eq(UserRoleEntity::getUserId, userId));
        List<Long> roleIds = userDetailVO.getRoleIds();
        List<UserRoleEntity> userRoles = roleIds.stream().map(e -> new UserRoleEntity()
                .setUserId(userId)
                .setRoleId(e)
                .setIsDelete(GlobalConstants.STATUS_NO)).collect(Collectors.toList());
        return Db.saveBatch(userRoles);
    }

    /**
     * 删除用户
     *
     * @param userIds 用户id
     * @return {@link Boolean}
     */
    @Override
    public Boolean delete(String userIds) {
        try {
            Assert.isTrue(CharSequenceUtil.isNotBlank(userIds), () -> new BusinessException(ResultCode.USER_ID_NULL));
            // 逻辑删除
            List<Long> ids = Arrays.stream(userIds.split(","))
                    .map(Long::parseLong).collect(Collectors.toList());
            return Db.removeByIds(ids, UserEntity.class);
        } catch (BusinessException e) {
            throw new BusinessException(e.getResultCode());
        }
    }
}