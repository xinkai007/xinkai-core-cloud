package com.xinkai.auth.service;

import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.security.Principal;
import java.util.Map;

/**
 * @className: AuthService
 * @description:
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/6/20
 **/
public interface AuthService {

    /**
     * 获得令牌
     *
     * @param principal  主要
     * @param parameters 参数
     * @return {@link OAuth2AccessToken}
     */
    OAuth2AccessToken getToken(Principal principal, Map<String, String> parameters);
}
