package com.xinkai.admin.boot.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @className: UserEntity
 * @description: 用户信息表
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户信息表", description = "")
@TableName("XK_USER")
public class UserEntity extends Model<UserEntity> implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(name = "主键ID")
    @TableId(type = IdType.AUTO)
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
     * 创建人
     */
    @ApiModelProperty(name = "创建人")
    private String createUser;
    /**
     * 创建时间
     */
    @ApiModelProperty(name = "创建时间")
    private Date createTime;
    /**
     * 更新人
     */
    @ApiModelProperty(name = "更新人")
    private String updateUser;
    /**
     * 更新时间
     */
    @ApiModelProperty(name = "更新时间")
    private Date updateTime;
    /**
     * 逻辑删除标识;(0:未删除，1:已删除)
     */
    @ApiModelProperty(name = "逻辑删除标识", notes = "(0:未删除，1:已删除)")
    @TableLogic(value = "0", delval = "1")
    private Integer isDelete;

}