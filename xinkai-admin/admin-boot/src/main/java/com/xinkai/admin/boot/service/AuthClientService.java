package com.xinkai.admin.boot.service;

import com.xinkai.admin.api.dto.ClientAuthDTO;

/**
 * @className: AuthClientService
 * @description: 服务接口
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
public interface AuthClientService {
    /**
     * 通过id获取客户端信息
     *
     * @param clientId 客户机id
     * @return {@link ClientAuthDTO}
     */
    ClientAuthDTO clientInfo(String clientId);
}