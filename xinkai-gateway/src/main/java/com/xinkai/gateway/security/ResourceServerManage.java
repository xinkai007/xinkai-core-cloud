package com.xinkai.gateway.security;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import com.xinkai.common.core.constant.GlobalConstants;
import com.xinkai.common.core.constant.SecurityConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @className: com.xinkai.cloud.gateway.security.ResourceServerManage
 * @description: 资源管理器
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/06/18
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class ResourceServerManage implements ReactiveAuthorizationManager<AuthorizationContext> {

    private final RedisTemplate redisTemplate;

    /**
     * 检查
     *
     * @param authentication       身份验证
     * @param authorizationContext 授权上下文
     * @return {@link Mono}<{@link AuthorizationDecision}>
     */
    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        //如果是预检请求直接放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(true));
        }
        PathMatcher pathMatcher = new AntPathMatcher();

        String methodValue = request.getMethodValue();
        //获取请求路径
        String path = request.getURI().getPath();
        String restfulPath = methodValue + ":" + path;
        //获取请求头中Token信息
        String token = request.getHeaders().getFirst(SecurityConstants.AUTHORIZATION_KEY);
        //判断Token中的前缀是否是Bearer，如果Token前缀是以Bearer开头则代表JWT有效且认证过
        if (CharSequenceUtil.isNotBlank(token) || CharSequenceUtil.startWithIgnoreCase(token, SecurityConstants.JWT_PREFIX)) {
            //特殊场景可配置直接访问权限
            if (path.contains("/test")) {
                return Mono.just(new AuthorizationDecision(false));
            }
        } else {
            return Mono.just(new AuthorizationDecision(false));
        }

        //从redis中获取角色的URL权限数据
        Map<String, Object> urlPermissionRoles = redisTemplate.opsForHash().entries(GlobalConstants.URL_PERM_ROLES_KEY);
        List<Object> authRoles = new ArrayList<>();
        // 是否需要鉴权，默认未设置拦截规则不需鉴权
        boolean requireCheck = false;
        for (Map.Entry<String, Object> permRole : urlPermissionRoles.entrySet()) {
            String url = permRole.getKey();
            if (pathMatcher.match(url, restfulPath)) {
                List<String> roles = Convert.toList(String.class, permRole.getValue());
                authRoles.addAll(roles);
                if (!requireCheck) {
                    requireCheck = true;
                }
            }
        }

        // 没有设置拦截规则放行
        if (!requireCheck) {
            return Mono.just(new AuthorizationDecision(true));
        }

        // 判断JWT中携带的用户角色是否有权限访问
        return authentication
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authority -> {
                    // ROLE_ADMIN移除前缀ROLE_得到用户的角色编码ADMIN
                    String roleCode = StrUtil.removePrefix(authority, SecurityConstants.AUTHORITY_PREFIX);
                    if (GlobalConstants.ROOT_ROLE_CODE.equals(roleCode)) {
                        // 如果是超级管理员则放行
                        return true;
                    }
                    return CollectionUtil.isNotEmpty(authRoles) && authRoles.contains(roleCode);
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }
}
