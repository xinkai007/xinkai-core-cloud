package com.xinkai.common.web.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONObject;
import com.xinkai.common.core.constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

/**
 * @className: com.xinkai.common.web.utils.UserUtils
 * @description: JWT工具类
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/06/22
 **/
@Slf4j
public class UserUtils {

    /**
     * 解析JWT获取用户ID
     *
     * @return {@link Long}
     */
    public static Long getUserId() {
        Long userId = null;
        JSONObject jwtPayload = JwtUtils.getJwtPayload();
        if (jwtPayload != null) {
            userId = jwtPayload.getLong("userId");
        }
        return userId;
    }

    /**
     * 解析JWT获取用户ID
     *
     * @return {@link Long}
     */
    public static Long getDeptId() {
        return JwtUtils.getJwtPayload().getLong("deptId");
    }

    /**
     * 解析JWT获取获取用户名
     *
     * @return {@link String}
     */
    public static String getUsername() {
        return JwtUtils.getJwtPayload().getStr(SecurityConstants.USER_NAME_KEY);
    }


    /**
     * JWT获取用户角色列表
     *
     * @return 角色列表
     */
    public static List<String> getRoles() {
        List<String> roles;
        JSONObject payload = JwtUtils.getJwtPayload();
        if (payload.containsKey(SecurityConstants.JWT_AUTHORITIES_KEY)) {
            roles = payload.getJSONArray(SecurityConstants.JWT_AUTHORITIES_KEY).toList(String.class);
        } else {
            roles = Collections.emptyList();
        }
        return roles;
    }

    /**
     * 是否「超级管理员」
     *
     * @return boolean
     */
    public static boolean isRoot() {
        List<String> roles = getRoles();
        return CollUtil.isNotEmpty(roles) && roles.contains("ROOT");
    }
}
