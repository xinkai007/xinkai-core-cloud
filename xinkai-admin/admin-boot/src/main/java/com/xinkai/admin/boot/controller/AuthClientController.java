package com.xinkai.admin.boot.controller;

import com.xinkai.admin.api.dto.ClientAuthDTO;
import com.xinkai.admin.boot.service.AuthClientService;
import com.xinkai.common.core.result.Result;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: AuthClientController
 * @description: 控制层
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Api(tags = "鉴权客户端表对象功能接口")
@RestController
@RequestMapping("/api/v1/authClient")
@RequiredArgsConstructor
public class AuthClientController {
    private final AuthClientService authClientService;

    /**
     * 通过id获取客户端
     *
     * @param clientId 客户机id
     * @return {@link Result}<{@link ClientAuthDTO}>
     */
    @GetMapping("/getClientById/{clientId}")
    public Result<ClientAuthDTO> getClientById(@PathVariable String clientId) {
        return Result.success(authClientService.getClientById(clientId));
    }
}