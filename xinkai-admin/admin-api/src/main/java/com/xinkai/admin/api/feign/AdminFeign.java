package com.xinkai.admin.api.feign;

import com.xinkai.admin.api.dto.ClientAuthDTO;
import com.xinkai.admin.api.dto.UserAuthDTO;
import com.xinkai.admin.api.feign.fallback.AdminFeignFallback;
import com.xinkai.common.core.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @className: com.xinkai.admin.api.feign.UserFeignClient
 * @description: 提供给鉴权服务接口
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/06/20
 **/
@FeignClient(value = "xinkai-admin", fallback = AdminFeignFallback.class)
public interface AdminFeign {
    /**
     * 通过id获取客户端
     *
     * @param clientId 客户端id
     * @return {@link Result}<{@link ClientAuthDTO}>
     */
    @GetMapping("/api/v1/auth_client/client_info/{clientId}")
    Result<ClientAuthDTO> clientInfo(@PathVariable String clientId);

    /**
     * 获取用户用户名
     *
     * @param userName 用户名
     * @return {@link Result}<{@link UserAuthDTO}>
     */
    @GetMapping("/api/v1/user/user_info/{userName}")
    Result<UserAuthDTO> getUserByUserName(@PathVariable String userName);
}
