package com.xinkai.admin.boot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinkai.admin.api.dto.UserAuthDTO;
import com.xinkai.admin.boot.pojo.dto.UserUpdatePasswordDTO;
import com.xinkai.admin.boot.pojo.dto.UserUpdateStatusDTO;
import com.xinkai.admin.boot.pojo.query.UserListQuery;
import com.xinkai.admin.boot.pojo.vo.UserDetailVO;
import com.xinkai.admin.boot.pojo.vo.UserInfoVO;
import com.xinkai.admin.boot.pojo.vo.UserListVO;

/**
 * @author xinkai
 * @className com.xinkai.admin.boot.service.UserService
 * @description 用户服务
 * @email xinkai8011@gmail.com
 * @date 2023/07/13
 **/
public interface UserService {
    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return {@link UserAuthDTO}
     */
    UserAuthDTO userInfo(String username);

    /**
     * 获取用户信息
     *
     * @return {@link UserInfoVO}
     */
    UserInfoVO loginUserInfo();

    /**
     * 用户列表查询
     *
     * @param userListQuery 用户列表查询
     * @return {@link IPage}<{@link UserListVO}>
     */
    IPage<UserListVO> pages(UserListQuery userListQuery);

    /**
     * 用户详细信息
     *
     * @param id id
     * @return {@link UserDetailVO}
     */
    UserDetailVO detail(Long id);

    /**
     * 修改用户状态
     *
     * @param userUpdateStatusDTO 用户更新状态dto
     * @return {@link Boolean}
     */
    Boolean updateStatus(UserUpdateStatusDTO userUpdateStatusDTO);

    /**
     * 修改用户密码
     *
     * @param userUpdatePasswordDTO 用户更新密码dto
     * @return {@link Boolean}
     */
    Boolean updatePassword(UserUpdatePasswordDTO userUpdatePasswordDTO);

    /**
     * 修改用户信息
     *
     * @param userDetailVO 用户详细签证官
     * @return {@link Boolean}
     */
    Boolean update(UserDetailVO userDetailVO);

    /**
     * 添加用户
     *
     * @param userDetailVO 用户详细签证官
     * @return {@link Boolean}
     */
    Boolean add(UserDetailVO userDetailVO);

    /**
     * 删除用户
     *
     * @param ids id
     * @return {@link Boolean}
     */
    Boolean delete(String ids);
}