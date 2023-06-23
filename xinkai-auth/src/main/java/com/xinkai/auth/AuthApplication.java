package com.xinkai.auth;

import com.xinkai.admin.api.feign.AdminFeign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @className: AuthApplication
 * @description: 鉴权服务
 * EnableCaching：开启基于注解的缓存
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/6/19
 **/

@EnableCaching
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = {AdminFeign.class})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
