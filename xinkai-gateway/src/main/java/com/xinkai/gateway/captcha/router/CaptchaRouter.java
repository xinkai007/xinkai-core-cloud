package com.xinkai.gateway.captcha.router;

import com.xinkai.gateway.captcha.handler.CaptchaHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @className: CaptchaRouter
 * @description: 验证码生成器接口
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/2/12
 **/
@Configuration
public class CaptchaRouter {

    /**
     * 获取验证码接口
     *
     * @param captchaHandler 验证码处理程序
     * @return {@link RouterFunction}<{@link ServerResponse}>
     */
    @Bean
    public RouterFunction<ServerResponse> captcha(CaptchaHandler captchaHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/captcha").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), captchaHandler);
    }
}
