package com.xinkai.admin.boot.service;

import com.xinkai.admin.api.dto.UserAuthDTO;
import com.xinkai.admin.boot.pojo.vo.UserInfoVO;

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
}