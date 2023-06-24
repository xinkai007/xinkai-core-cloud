package com.xinkai.auth.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xinkai.auth.service.AuthService;
import com.xinkai.common.core.constant.SecurityConstants;
import com.xinkai.common.web.utils.JwtUtils;
import com.xinkai.common.web.utils.RequestUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @className: AuthServiceImpl
 * @description:
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/6/20
 **/
@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private TokenEndpoint tokenEndpoint;

    private final RedisTemplate redisTemplate;

    /**
     * 获得令牌
     *
     * @param principal  主要
     * @param parameters 参数
     * @return {@link OAuth2AccessToken}
     */
    @Override
    public OAuth2AccessToken getToken(Principal principal, Map<String, String> parameters) {
        OAuth2AccessToken accessToken = null;
        try {
            /**
             * 获取登录认证的客户端ID
             *
             * 兼容两种方式获取Oauth2客户端信息（client_id、client_secret）
             * 方式一：client_id、client_secret放在请求路径中(注：当前版本已废弃)
             * 方式二：放在请求头（Request Headers）中的Authorization字段，且经过加密，例如 Basic Y2xpZW50OnNlY3JldA== 明文等于 client:secret
             */
            String clientId = RequestUtils.getAuthClientId();
            log.info("OAuth认证授权 客户端ID:{}，请求参数：{}", clientId, JSONUtil.toJsonStr(parameters));
            /**
             * knife4j接口文档测试使用
             *
             * 请求头自动填充，token必须原生返回，不能有任何包装，否则显示 undefined undefined
             * 账号/密码:  client_id/client_secret : client/123456
             */
            if (SecurityConstants.TEST_CLIENT_ID.equals(clientId)) {
                return tokenEndpoint.postAccessToken(principal, parameters).getBody();
            }
            accessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return accessToken;
    }

    /**
     * 注销
     *
     * @return {@link String}
     */
    @Override
    public String logOut() {
        JSONObject payload = JwtUtils.getJwtPayload();
        // JWT唯一标识
        String jti = payload.getStr(SecurityConstants.JWT.JTI);
        // JWT过期时间戳(单位：秒)
        Long expireTime = payload.getLong(SecurityConstants.JWT.EXP);
        if (expireTime != null) {
            // 当前时间（单位：秒）
            long currentTime = System.currentTimeMillis() / 1000;
            // token未过期，添加至缓存作为黑名单限制访问，缓存时间为token过期剩余时间
            if (expireTime > currentTime) {
                redisTemplate.opsForValue().set(SecurityConstants.RedisHeader.TOKEN_BLACKLIST_PREFIX + jti, null, (expireTime - currentTime), TimeUnit.SECONDS);
            }
        } else {
            // token 永不过期则永久加入黑名单
            redisTemplate.opsForValue().set(SecurityConstants.RedisHeader.TOKEN_BLACKLIST_PREFIX + jti, null);
        }
        return "注销成功!";
    }
}
