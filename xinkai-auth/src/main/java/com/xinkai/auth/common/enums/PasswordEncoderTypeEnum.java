package com.xinkai.auth.common.enums;

import lombok.Getter;

/**
 * @className: com.xinkai.auth.common.enums.PasswordEncoderTypeEnum
 * @description: 密码编码类型枚举
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/06/20
 **/
public enum PasswordEncoderTypeEnum {
    /**
     * BCRYPT加密
     */
    BCRYPT("{bcrypt}", "BCRYPT加密"),
    /**
     * 无加密明文
     */
    NOOP("{noop}", "无加密明文");
    /**
     * 前缀
     */
    @Getter
    private String prefix;

    /**
     * 密码编码枚举类型
     *
     * @param prefix 前缀
     * @param desc   desc
     */
    PasswordEncoderTypeEnum(String prefix, String desc) {
        this.prefix = prefix;
    }
}
