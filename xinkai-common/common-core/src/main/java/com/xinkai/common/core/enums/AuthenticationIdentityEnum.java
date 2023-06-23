package com.xinkai.common.core.enums;

import com.xinkai.common.core.base.IBaseEnum;
import lombok.Getter;

/**
 * @className: AuthenticationIdentityEnum
 * @description: 认证身份标识枚举
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2022/6/5
 **/
public enum AuthenticationIdentityEnum implements IBaseEnum<String> {

    USERNAME("username", "用户名"),
    MOBILE("mobile", "手机号"),
    OPENID("openId", "开放式认证系统唯一身份标识");

    /**
     * 值
     */
    @Getter
    private final String value;

    /**
     * 标签
     */
    @Getter
    private final String label;

    AuthenticationIdentityEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }
}
