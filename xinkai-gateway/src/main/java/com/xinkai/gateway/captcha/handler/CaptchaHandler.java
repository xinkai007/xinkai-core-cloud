package com.xinkai.gateway.captcha.handler;

import cn.hutool.core.util.IdUtil;
import com.wf.captcha.base.Captcha;
import com.xinkai.common.core.result.Result;
import com.xinkai.common.redis.constant.RedisKeyPrefixConstants;
import com.xinkai.gateway.annotaion.CaptchaType;
import com.xinkai.gateway.annotaion.CaptchaValueTtl;
import com.xinkai.gateway.captcha.component.CaptchaProducer;
import com.xinkai.gateway.captcha.enums.CaptchaTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @className: com.xinkai.cloud.gateway.captcha.handler.CaptchaHandler
 * @description: 验证码处理程序
 * 验证码处理器主要是将验证码结果存入Redis
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/06/18
 **/
@Component
@RequiredArgsConstructor
public class CaptchaHandler implements HandlerFunction<ServerResponse> {
    /**
     * 验证码生产商
     */
    private final CaptchaProducer captchaProducer;

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 验证码类型默认算术可根据需求调整
     */
    @CaptchaType
    CaptchaTypeEnum captchaType;
    /**
     * 验证码值的有效期(单位:秒)，默认:120
     */
    @CaptchaValueTtl
    long captchaValueTtl;

    /**
     * 处理
     * 实现HandlerFunction.handle将验证码返回
     *
     * @param request 请求
     * @return {@link Mono}<{@link ServerResponse}>
     */
    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        //1.获取生成的验证码信息
        Captcha captcha = captchaProducer.getCaptcha(captchaType);
        String captchaValue = captcha.text();
        //2.将生成的验证码信息缓存至Redis
        String uuid = IdUtil.simpleUUID();
        stringRedisTemplate.opsForValue().set(RedisKeyPrefixConstants.VALIDATION_CODE_KEY_PREFIX + uuid, captchaValue, captchaValueTtl, TimeUnit.SECONDS);
        //3.将生成的验证码图片信息返回
        String captchaBase64 = captcha.toBase64();
        Map<String, String> result = new HashMap<>(2);
        result.put("uuid", uuid);
        result.put("img", captchaBase64);
        return ServerResponse.ok().body(BodyInserters.fromValue(Result.success(result)));
    }
}
