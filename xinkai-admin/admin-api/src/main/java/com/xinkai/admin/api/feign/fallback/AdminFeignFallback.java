package com.xinkai.admin.api.feign.fallback;

import com.xinkai.admin.api.dto.ClientAuthDTO;
import com.xinkai.admin.api.dto.UserAuthDTO;
import com.xinkai.admin.api.feign.AdminFeign;
import com.xinkai.common.core.result.Result;
import com.xinkai.common.core.result.ResultCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @className: UserFeignFallbackClient
 * @description: 管理系统服务调用异常降级
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/6/20
 **/
@Slf4j
public class AdminFeignFallback implements AdminFeign {
    /**
     * 通过id获取客户端
     *
     * @param clientId 客户端id
     * @return {@link Result}<{@link ClientAuthDTO}>
     */
    @Override
    public Result<ClientAuthDTO> clientInfo(String clientId) {
        log.error("AdminFeignFallback.getClientById feign远程获取客户端异常！");
        return Result.failed(ResultCode.DEGRADATION);
    }

    /**
     * 获取用户用户名
     *
     * @param userName 用户名
     * @return {@link Result}<{@link UserAuthDTO}>
     */
    @Override
    public Result<UserAuthDTO> getUserByUserName(String userName) {
        log.error("AdminFeignFallback.getClientById feign远程获取系统用户信息异常！");
        return Result.failed(ResultCode.DEGRADATION);
    }
}
