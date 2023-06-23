package com.xinkai.auth.security.extension.captcha;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.CharSequenceUtil;
import com.xinkai.common.core.constant.SecurityConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @className: com.xinkai.auth.security.extension.captcha.CaptchaTokenGranter
 * @description: 验证码授权模式授权者
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/06/22
 **/
public class CaptchaTokenGranter extends AbstractTokenGranter {

    /**
     * 声明授权者 CaptchaTokenGranter 支持授权模式 captcha
     * 根据接口传值 grant_type = captcha 的值匹配到此授权者
     * 匹配逻辑详见下面的两个方法
     *
     * @see CompositeTokenGranter#grant(String, TokenRequest)
     * @see AbstractTokenGranter#grant(String, TokenRequest)
     */
    private static final String GRANT_TYPE = "captcha";
    private final AuthenticationManager authenticationManager;
    private final StringRedisTemplate redisTemplate;

    public CaptchaTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService,
                               OAuth2RequestFactory requestFactory, AuthenticationManager authenticationManager,
                               StringRedisTemplate redisTemplate
    ) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.authenticationManager = authenticationManager;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 得到oauth2身份认证
     *
     * @param client       客户端
     * @param tokenRequest 令牌请求
     * @return {@link OAuth2Authentication}
     */
    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());

        // 验证码校验逻辑
        String validateCode = parameters.get("code");
        String uuid = parameters.get("uuid");

        Assert.isTrue(CharSequenceUtil.isNotBlank(validateCode), "验证码不能为空!");
        String validateCodeKey = SecurityConstants.JWT.VALIDATION_CODE_KEY_PREFIX + uuid;

        // 从缓存取出正确的验证码和用户输入的验证码比对
        String correctValidateCode = redisTemplate.opsForValue().get(validateCodeKey);
        Assert.isTrue(CharSequenceUtil.isNotBlank(correctValidateCode), "验证码已过期!");
        Assert.isTrue(validateCode.equals(correctValidateCode), "您输入的验证码不正确!");

        // 验证码验证通过，删除 Redis 的验证码
        redisTemplate.delete(validateCodeKey);

        String username = parameters.get("username");
        String password = parameters.get("password");

        // 移除后续无用参数
        parameters.remove("password");
        parameters.remove("code");
        parameters.remove("uuid");

        // 和密码模式一样的逻辑
        Authentication userAuth = new UsernamePasswordAuthenticationToken(username, password);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);

        try {
            userAuth = this.authenticationManager.authenticate(userAuth);
        } catch (AccountStatusException | BadCredentialsException var8) {
            throw new InvalidGrantException(var8.getMessage());
        }

        if (userAuth != null && userAuth.isAuthenticated()) {
            OAuth2Request storedAuth2Request = this.getRequestFactory().createOAuth2Request(client, tokenRequest);
            return new OAuth2Authentication(storedAuth2Request, userAuth);
        } else {
            throw new InvalidGrantException("Could not authenticate user: " + username);
        }
    }
}
