package com.xinkai.admin.boot.controller;

import com.xinkai.admin.api.dto.UserAuthDTO;
import com.xinkai.admin.boot.pojo.vo.UserInfoVO;
import com.xinkai.admin.boot.service.UserService;
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
    @GetMapping("/getUserByUserName/{userName}")
    public Result<UserAuthDTO> getUserByUserName(@PathVariable String userName) {
        return Result.success(userService.getUserByUserName(userName));
    }

    /**
     * 获取用户信息
     *
     * @return {@link Result}<{@link UserInfoVO}>
     */
    @GetMapping("/getUserInfo")
    public Result<UserInfoVO> getUserInfo() {
        return Result.success(userService.getUserInfo());
    }
}