package com.xinkai.cloud.common.constant;

/**
 * 安全常量
 *
 * @author xinkai
 * @className: SecurityConstants
 * @description: 配置网关常量信息
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/2/15
 */
public interface SecurityConstants {

    /**
     * jwt
     *
     * @author xinkai
     * @date 2023/04/09
     */
    interface JWT {
        /**
         * JWT ID 唯一标识
         */
        String JTI = "jti";
        /**
         * JWT载体key
         */
        String PAYLOAD_KEY = "payload";
    }

    /**
     * redis key 头部
     *
     * @author xinkai
     * @date 2023/04/10
     */
    interface RedisHeader {
        /**
         * 黑名单token前缀
         */
        String TOKEN_BLACKLIST_PREFIX = "auth:blacklist:";
    }

    /**
     * env
     * 环境
     *
     * @author xinkai
     * @date 2023/04/10
     */
    interface Env {
        String DEV = "dev";
        String SIT = "sit";
        String PROD = "prod";
    }

    /**
     * 认证请求头key
     */
    String AUTHORIZATION_KEY = "Authorization";

    /**
     * JWT令牌前缀
     */
    String JWT_PREFIX = "Bearer ";

}
