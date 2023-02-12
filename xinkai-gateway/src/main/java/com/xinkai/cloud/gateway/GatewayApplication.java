package com.xinkai.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @className: GatewayApplication
 * @description: 应用启动类
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/2/11
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
