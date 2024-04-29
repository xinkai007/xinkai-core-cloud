package com.xinkai.gateway.security;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Assert;
import com.xinkai.common.core.constant.SecurityConstants;
import com.xinkai.common.core.exception.SystemException;
import com.xinkai.common.core.result.ResultCode;
import com.xinkai.gateway.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import reactor.core.publisher.Mono;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;

/**
 * @className: com.xinkai.cloud.gateway.security.ResourceSecurityConfig
 * @description: 资源安全配置类
 * ConfigurationProperties(prefix = "security")：获取配置文件中资源访问权限
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/06/18
 **/
@Slf4j
@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "security")
public class ResourceSecurityConfig {

    private final ResourceServerManage resourceServerManage;

    /**
     * 访问白名单url
     */
    @Setter
    private List<String> ignoreUrls;

    /**
     * 安全web过滤器链
     *
     * @param security 安全
     * @return {@link SecurityWebFilterChain}
     * @throws SystemException 系统异常
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity security) throws SystemException {
        Assert.notNull(ignoreUrls, () -> new SystemException(ResultCode.CLIENT_AUTHENTICATION_FAILED));
        security.oauth2ResourceServer()
                .jwt()
                //重新定义权限管理器
                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                //加载本地公钥
                .publicKey(rsaPublicKey());
        //自定义token无效或者已过期响应
        security.oauth2ResourceServer().authenticationEntryPoint(authenticationEntryPoint());

        security.authorizeExchange()
                //访问路径白名单
                .pathMatchers(Convert.toStrArray(ignoreUrls))
                .permitAll()
                .anyExchange().access(resourceServerManage)
                .and()
                //异常处理
                .exceptionHandling()
                //自定义未授权处理
                .accessDeniedHandler(accessDeniedHandler())
                //自定义未认证处理
                .authenticationEntryPoint(authenticationEntryPoint())
                .and().csrf().disable();
        return security.build();
    }

    /**
     * 拒绝访问处理程序
     *
     * @return {@link ServerAccessDeniedHandler}
     */
    @Bean
    ServerAccessDeniedHandler accessDeniedHandler() {
        log.info("拒绝访问处理程序");
        return ((exchange, denied) -> Mono.defer(() -> Mono.just(exchange.getResponse()))
                .flatMap(response -> ResponseUtils.writeErrorInfo(response, ResultCode.ACCESS_UNAUTHORIZED)));
    }

    /**
     * 认证入口点
     * 自定义token无效或者已过期响应
     *
     * @return {@link ServerAuthenticationEntryPoint}
     */
    @Bean
    ServerAuthenticationEntryPoint authenticationEntryPoint() {
        log.info("认证入口点");
        return ((exchange, ex) -> Mono.defer(() -> Mono.just(exchange.getResponse()))
                .flatMap(response -> ResponseUtils.writeErrorInfo(response, ResultCode.TOKEN_INVALID_OR_EXPIRED)));
    }

    /**
     * jwt认证转换器
     *
     * @return {@link Converter}<{@link Jwt}, {@link ?} {@link extends} {@link Mono}<{@link ?} {@link extends} {@link AbstractAuthenticationToken}>>
     */
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwt = new JwtGrantedAuthoritiesConverter();
        jwt.setAuthorityPrefix(SecurityConstants.AUTHORITY_PREFIX);
        jwt.setAuthoritiesClaimName(SecurityConstants.JWT_AUTHORITIES_KEY);

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

    /**
     * rsa公钥
     * 获取本地签名公钥
     *
     * @return {@link RSAPublicKey}
     */
    @SneakyThrows
    @Bean
    public RSAPublicKey rsaPublicKey() {
        Resource resource = new ClassPathResource("public.key");
        InputStream is = resource.getInputStream();
        String key = IoUtil.read(is).toString();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec((Base64.decode(key)));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }
}
