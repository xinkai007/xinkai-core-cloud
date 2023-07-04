package com.xinkai.admin.boot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinkai.admin.api.dto.UserAuthDTO;
import com.xinkai.admin.boot.pojo.query.UserListQuery;
import com.xinkai.admin.boot.pojo.vo.UserInfoVO;
import com.xinkai.admin.boot.pojo.vo.UserListVO;
import com.xinkai.admin.boot.service.UserService;
import com.xinkai.common.core.result.PageResult;
import com.xinkai.common.core.result.Result;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: UserController
 * @description: 控制层
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Api(tags = "用户信息表对象功能接口")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 获取用户用户名
     *
     * @param userName 用户名
     * @return {@link Result}<{@link UserAuthDTO}>
     */
    @GetMapping("/user_info/{userName}")
    public Result<UserAuthDTO> userInfo(@PathVariable String userName) {
        return Result.success(userService.userInfo(userName));
    }

    /**
     * 获取用户信息
     *
     * @return {@link Result}<{@link UserInfoVO}>
     */
    @GetMapping("/login_user_info")
    public Result<UserInfoVO> loginUserInfo() {
        return Result.success(userService.loginUserInfo());
    }

    /**
     * 用户列表查询
     *
     * @param userListQuery 用户列表查询
     * @return {@link Result}<{@link IPage}<{@link UserListVO}>>
     */
    @GetMapping("/pages")
    public PageResult<UserListVO> pages(UserListQuery userListQuery) {
        return PageResult.success(userService.pages(userListQuery));
    }
}