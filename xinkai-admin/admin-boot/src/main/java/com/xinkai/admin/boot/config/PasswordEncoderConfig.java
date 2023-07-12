package com.xinkai.admin.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @className: com.xinkai.admin.boot.config.PasswordEncoderConfig
 * @description: 密码编码器
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/07/06
 **/
@Configuration
public class PasswordEncoderConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
