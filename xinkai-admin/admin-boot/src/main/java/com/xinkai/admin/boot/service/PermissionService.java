package com.xinkai.admin.boot.service;

/**
 * @className: PermissionService
 * @description: 服务接口
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
public interface PermissionService {

    /**
     * 刷新Redis缓存中角色菜单的权限规则
     * 角色和菜单信息变更调用
     *
     * @return {@link Boolean}
     */
    Boolean refreshPermRolesRules();
}