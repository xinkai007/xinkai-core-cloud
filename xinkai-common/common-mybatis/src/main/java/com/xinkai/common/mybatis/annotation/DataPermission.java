package com.xinkai.common.mybatis.annotation;

import java.lang.annotation.*;

/**
 * @className: com.xinkai.common.mybatis.annotation.DataPermission
 * @description: 数据权限注解
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/06/23
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DataPermission {

    /**
     * 数据权限 {@link com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor}
     */
    String deptAlias() default "";
}

