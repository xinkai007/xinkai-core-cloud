package com.xinkai.auth.pojo.query;

import lombok.Data;

/**
 * @className: com.xinkai.auth.pojo.query.TokenQuery
 * @description: Token查询对象
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/06/20
 **/
@Data
public class TokenQuery {
    /**
     * 授权模式
     */
    private String grant_type;
    /**
     * Oauth2客户端ID
     */
    private String client_id;
    /**
     * Oauth2客户端秘钥
     */
    private String client_secret;
    /**
     * 刷新token
     */
    private String refresh_token;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
