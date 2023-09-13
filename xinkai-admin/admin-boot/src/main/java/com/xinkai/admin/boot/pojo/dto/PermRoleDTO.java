package com.xinkai.admin.boot.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author xinkai
 * @className PermRoleDTO
 * @description
 * @email xinkai8011@gmail.com
 * @date 2023/9/11
 **/
@Data
@Accessors(chain = true)
public class PermRoleDTO {
    /**
     * ID
     */
    private Long id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 菜单模块ID
     */
    private Long menuId;
    /**
     * URL权限标识
     */
    private String urlPerm;
    /**
     * 按钮权限标识
     */
    private String btnPerm;
    /**
     * 有权限的角色编号
     */
    private List<String> roles;
}
