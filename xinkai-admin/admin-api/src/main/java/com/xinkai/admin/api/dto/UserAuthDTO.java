package com.xinkai.admin.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @className: UserAuthDTO
 * @description:
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/6/20
 **/
@Data
@Accessors(chain = true)
public class UserAuthDTO {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户状态(1:正常;0:禁用)
     */
    private Integer status;
    /**
     * 用户角色编码集合 ["ROOT","ADMIN"]
     */
    private List<String> roles;
    /**
     * 部门ID
     */
    private Long deptId;
}
