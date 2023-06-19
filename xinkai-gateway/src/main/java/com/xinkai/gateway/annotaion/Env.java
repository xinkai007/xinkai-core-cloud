package com.xinkai.gateway.annotaion;

import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className: Env
 * @description:系统环境
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/4/10
 **/
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Value("${spring.profiles.active}")
public @interface Env {
}
