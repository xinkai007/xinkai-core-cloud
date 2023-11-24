package com.xinkai.auth.controller;

import com.xinkai.auth.service.AuthService;
import com.xinkai.common.core.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.Map;

/**
 * @author xinkai
 * @className com.xinkai.auth.controller.AuthController
 * @description 身份验证控制器
 * @email xinkai8011@gmail.com
 * @date 2023/11/23
 **/
@Api(tags = "认证中心")
@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    /**
     * 获得令牌
     *
     * @param principal  主要
     * @param parameters 参数
     * @return {@link Result}<{@link OAuth2AccessToken}>
     */
    @PostMapping("/token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type", defaultValue = "password", value = "授权模式", required = true),
            @ApiImplicitParam(name = "client_id", defaultValue = "client", value = "Oauth2客户端ID", required = true),
            @ApiImplicitParam(name = "client_secret", defaultValue = "123456", value = "Oauth2客户端秘钥", required = true),
            @ApiImplicitParam(name = "refresh_token", value = "刷新token"),
            @ApiImplicitParam(name = "username", defaultValue = "admin", value = "用户名"),
            @ApiImplicitParam(name = "password", defaultValue = "123456", value = "用户密码")
    })
    public Result<OAuth2AccessToken> getToken(@ApiIgnore Principal principal, @ApiIgnore @RequestParam Map<String, String> parameters) {
        return Result.success(authService.getToken(principal, parameters));
    }

    /**
     * 注销
     *
     * @return {@link Result}
     */
    @PostMapping("/logout")
    public Result<String> logOut() {
        return Result.success(authService.logOut());
    }
}
