package com.xinkai.admin.boot.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author xinkai
 * @className com.xinkai.admin.boot.pojo.vo.UserDetailVO
 * @description 用户详细签证官
 * @email xinkai8011@gmail.com
 * @date 2023/07/13
 **/
@Data
public class UserDetailVO {
    /**
     * id
     */
    private Long id;
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
     */
    private String password;
    /**
     * 移动
     */
    private String mobile;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * 性别
     */
    private String gender;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 角色id
     */
    private List<Integer> roleIds;
}
