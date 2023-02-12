package com.xinkai.cloud.gateway.annotaion;

import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className: CaptchaValueTtl
 * @description: 验证码有效期
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/2/12
 **/
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Value("${captcha.ttl:120}")
public @interface CaptchaValueTtl {
}
