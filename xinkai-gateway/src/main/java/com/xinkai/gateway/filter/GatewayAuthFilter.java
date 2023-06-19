package com.xinkai.gateway.filter;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.nimbusds.jose.JWSObject;
import com.xinkai.common.core.constant.SecurityConstants;
import com.xinkai.common.core.result.ResultCode;
import com.xinkai.gateway.annotaion.Env;
import com.xinkai.gateway.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;

/**
 * @className: com.xinkai.cloud.gateway.filter.GatewayAuthFilter
 * @description: 拦截所有经过网关的请求进行校验处理
 * GatewayFilter和GlobalFilter的作用一样，区别在于GlobalFilter的逻辑可以写代码来自定义规则。
 * 而GatewayFilter通过配置定义，处理逻辑是固定的。
 * 1.实现GlobalFilter自定义规则
 * 2.该组件是自定义的网关过滤器，主要用来将解析后的token信息存放在请求头中，转发给各个服务。
 * JWT是JsonWebToken的简称
 * JWT分为三部分组成：header、payload、secret
 * header:客户端类型
 * payload:存储当前登录用户的一些基本信息
 * secret:加密key
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/06/18
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class GatewayAuthFilter implements GlobalFilter, Ordered {

    private final RedisTemplate redisTemplate;

    @Env
    private String env;

    /**
     * 过滤器
     *
     * @param exchange 交换
     * @param chain    链
     * @return {@link Mono}<{@link Void}>
     */
    @Override
    @SneakyThrows
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //0.指定环境特定逻辑处理
        this.EnvFilter(request, response);
        //1.从请求头中获取token
        String token = request.getHeaders().getFirst(SecurityConstants.AUTHORIZATION_KEY);
        //2.校验所有请求中的token前缀是否是正确的
        if (CharSequenceUtil.isBlank(token) && !CharSequenceUtil.startWithIgnoreCase(token, SecurityConstants.JWT_PREFIX)) {
            return chain.filter(exchange);
        }
        //3.解析JWT获取jti，以jti为key判断redis的黑名单列表是否存在，存在则拦截访问
        token = CharSequenceUtil.replaceIgnoreCase(token, SecurityConstants.JWT_PREFIX, Strings.EMPTY);
        String payload = StrUtil.toString(JWSObject.parse(token).getPayload());
        //4.将解析后的token转换成JSON对象
        JSONObject tokenJsonObj = JSONUtil.parseObj(payload);
        String jti = tokenJsonObj.getStr(SecurityConstants.JWT.JTI);
        //5.校验Redis中的黑名单列表是否包含当前用户
        boolean isBoolean = redisTemplate.hasKey(SecurityConstants.RedisHeader.TOKEN_BLACKLIST_PREFIX + jti);
        if (isBoolean) {
            return ResponseUtils.writeErrorInfo(response, ResultCode.TOKEN_ACCESS_FORBIDDEN);
        }
        //6.当所有校验通过后，写入JWT信息放入request
        request = exchange.getRequest().mutate().header(SecurityConstants.JWT.PAYLOAD_KEY, URLEncoder.encode(payload, "UTF-8")).build();
        exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }


    /**
     * 特定环境过滤器
     *
     * @return boolean
     */
    private void EnvFilter(ServerHttpRequest request, ServerHttpResponse response) {
        switch (env) {
            case SecurityConstants.Env.PROD:
                break;
            case SecurityConstants.Env.SIT:
                break;
            case SecurityConstants.Env.DEV:
                break;
            default:
        }
    }


    /**
     * 设置网关过滤器的优先级
     *
     * @return int
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
