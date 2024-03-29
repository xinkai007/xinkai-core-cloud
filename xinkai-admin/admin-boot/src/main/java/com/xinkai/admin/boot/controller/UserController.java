package com.xinkai.admin.boot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinkai.admin.api.dto.UserAuthDTO;
import com.xinkai.admin.boot.pojo.dto.UserUpdatePasswordDTO;
import com.xinkai.admin.boot.pojo.dto.UserUpdateStatusDTO;
import com.xinkai.admin.boot.pojo.query.UserListQuery;
import com.xinkai.admin.boot.pojo.vo.UserDetailVO;
import com.xinkai.admin.boot.pojo.vo.UserInfoVO;
import com.xinkai.admin.boot.pojo.vo.UserListVO;
import com.xinkai.admin.boot.service.UserService;
import com.xinkai.common.core.result.PageResult;
import com.xinkai.common.core.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author xinkai
 * @className com.xinkai.admin.boot.controller.UserController
 * @description 用户控制器
 * @email xinkai8011@gmail.com
 * @date 2023/07/13
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

    /**
     * 细节
     * 用户详细信息
     *
     * @param id id
     * @return {@link Result}<{@link UserDetailVO}>
     */
    @ApiOperation(value = "用户详细信息")
    @GetMapping("/detail/{id}")
    public Result<UserDetailVO> detail(@PathVariable Long id) {
        return Result.success(userService.detail(id));
    }

    /**
     * 更新状态
     * 修改用户状态
     *
     * @param userUpdateStatusDTO 用户更新状态dto
     * @return {@link Result}<{@link Boolean}>
     */
    @ApiOperation(value = "修改用户状态")
    @PostMapping("/update_status")
    public Result<Boolean> updateStatus(@RequestBody UserUpdateStatusDTO userUpdateStatusDTO) {
        return Result.judge(userService.updateStatus(userUpdateStatusDTO));
    }

    /**
     * 更新密码
     * 修改用户密码
     *
     * @param userUpdatePasswordDTO 用户更新密码dto
     * @return {@link Result}<{@link Boolean}>
     */
    @ApiOperation(value = "修改用户密码")
    @PostMapping("/update_password")
    public Result<Boolean> updatePassword(@RequestBody UserUpdatePasswordDTO userUpdatePasswordDTO) {
        return Result.judge(userService.updatePassword(userUpdatePasswordDTO));
    }

    /**
     * 修改用户信息
     *
     * @param userDetailVO 用户详细签证官
     * @return {@link Result}<{@link Boolean}>
     */
    @ApiOperation(value = "修改用户信息")
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody UserDetailVO userDetailVO) {
        return Result.judge(userService.update(userDetailVO));
    }

    @ApiOperation(value = "新增用户信息")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody UserDetailVO userDetailVO) {
        return Result.judge(userService.add(userDetailVO));
    }

    @ApiOperation(value = "新增用户信息")
    @DeleteMapping("/delete/{ids}")
    public Result<Boolean> delete(@ApiParam("用户ID多个以英文逗号分割") @PathVariable String ids) {
        return Result.judge(userService.delete(ids));
    }
}