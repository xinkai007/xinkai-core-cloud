package com.xinkai.common.core.constant;

/**
 * 全局常量
 *
 * @author xinkai
 * @className: GlobalConstants
 * @description:
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/5/25
 */
public interface GlobalConstants {
    /**
     * 全局状态:是
     */
    Integer STATUS_YES = 1;

    /**
     * 全局状态:否
     */
    Integer STATUS_NO = 0;

    /**
     * 默认密码
     */
    String DEFAULT_PASSWORD = "xinkai@tech";
    /**
     * [ {接口路径:[角色编码]},...]
     */
    String URL_PERM_ROLES_KEY = "system:perm_roles_rule:url";

    /**
     * 超级管理员角色编码
     */
    String ROOT_ROLE_CODE = "ROOT";
}
