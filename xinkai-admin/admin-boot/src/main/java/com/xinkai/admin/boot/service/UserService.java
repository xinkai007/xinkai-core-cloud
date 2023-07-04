package com.xinkai.admin.boot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinkai.admin.api.dto.UserAuthDTO;
import com.xinkai.admin.boot.pojo.query.UserListQuery;
import com.xinkai.admin.boot.pojo.vo.UserInfoVO;
import com.xinkai.admin.boot.pojo.vo.UserListVO;

/**
 * @className: UserService
 * @description: 服务接口
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
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
}