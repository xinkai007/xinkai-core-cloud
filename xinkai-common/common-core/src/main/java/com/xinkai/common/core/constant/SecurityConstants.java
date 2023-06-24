package com.xinkai.common.core.constant;

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
         * jwt密钥文件名
         */
        String JKS = "jwt.jks";
        /**
         * JWT载体key
         */
        String PAYLOAD_KEY = "payload";
        /**
         * JWT令牌前缀
         */
        String JWT_PREFIX = "Bearer ";
        /**
         * 私钥密码
         */
        String PRIVATE_KEY_PASSWORD = "123456";
        /**
         * 验证码key前缀
         */
        String VALIDATION_CODE_KEY_PREFIX = "CAPTCHA:";

        String EXP = "exp";
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
     * JWT存储权限前缀
     */
    String AUTHORITY_PREFIX = "ROLE_";

    /**
     * JWT存储权限属性
     */
    String JWT_AUTHORITIES_KEY = "authorities";


    /**
     * 接口文档 Knife4j 测试客户端ID
     */
    String TEST_CLIENT_ID = "client";

    /**
     * Basic认证前缀
     */
    String BASIC_PREFIX = "Basic ";

    /**
     * 客户端idKey
     */
    String CLIENT_ID_KEY = "client_id";

    /**
     * 系统管理 web 客户端ID
     */
    String ADMIN_CLIENT_ID = "admin-web";

    /**
     * 认证身份标识
     */
    String AUTHENTICATION_IDENTITY_KEY = "authenticationIdentity";

    /**
     * 刷新令牌密钥
     */
    String REFRESH_TOKEN_KEY = "refresh_token";

    /**
     * 用户名Key
     */
    String USER_NAME_KEY = "username";
}
