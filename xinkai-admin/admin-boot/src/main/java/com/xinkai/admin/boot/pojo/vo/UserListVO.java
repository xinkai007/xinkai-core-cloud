package com.xinkai.admin.boot.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @className: UserListVO
 * @description: 用户列表查询视图层对象
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/6/25
 **/
@Data
@Accessors(chain = true)
public class UserListVO {
    /**
     * 主键ID
     */
    @ApiModelProperty(name = "主键ID")
    private Long id;
    /**
     * 用户名
     */
    @ApiModelProperty(name = "用户名")
    private String userName;
    /**
     * 昵称
     */
    @ApiModelProperty(name = "昵称")
    private String nickName;
    /**
     * 性别;(1:男，2:女)
     */
    @ApiModelProperty(name = "性别", notes = "(1:男，2:女)")
    private Integer gender;
    /**
     * 性别
     */
    @ApiModelProperty(name = "性别")
    private String genderLabel;
    /**
     * 密码
     */
    @ApiModelProperty(name = "密码")
    private String password;
    /**
     * 部门ID
     */
    @ApiModelProperty(name = "部门ID")
    private Long deptId;
    /**
     * 部门名称
     */
    @ApiModelProperty(name = "部门名称")
    private String deptName;
    /**
     * 用户头像
     */
    @ApiModelProperty(name = "用户头像")
    private String avatar;
    /**
     * 联系方式
     */
    @ApiModelProperty(name = "联系方式")
    private String mobile;
    /**
     * 用户状态;(1:正常，0:禁用)
     */
    @ApiModelProperty(name = "用户状态", notes = "(1:正常，0:禁用)")
    private Integer status;
    /**
     * 用户邮箱
     */
    @ApiModelProperty(name = "用户邮箱")
    private String email;
    /**
     * 创建时间
     */
    @ApiModelProperty(name = "创建时间")
    private String createTime;
}
