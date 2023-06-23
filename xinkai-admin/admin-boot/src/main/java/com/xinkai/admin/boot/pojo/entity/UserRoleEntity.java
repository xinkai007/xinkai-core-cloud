package com.xinkai.admin.boot.pojo.entity;

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
 * @className: UserRoleEntity
 * @description: 用户和角色关联表
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户和角色关联表", description = "")
@TableName("XK_USER_ROLE")
public class UserRoleEntity extends Model<UserRoleEntity> implements Serializable {
    /**
     * 用户ID
     */
    @ApiModelProperty(name = "用户ID", notes = "")
    private Integer userId;
    /**
     * 角色ID
     */
    @ApiModelProperty(name = "角色ID", notes = "")
    private Integer roleId;
    /**
     * 创建人
     */
    @ApiModelProperty(name = "创建人", notes = "")
    private String createUser;
    /**
     * 创建时间
     */
    @ApiModelProperty(name = "创建时间", notes = "")
    private Date createTime;
    /**
     * 更新人
     */
    @ApiModelProperty(name = "更新人", notes = "")
    private String updateUser;
    /**
     * 更新时间
     */
    @ApiModelProperty(name = "更新时间", notes = "")
    private Date updateTime;
    /**
     * 逻辑删除标识;(0:未删除，1:已删除)
     */
    @ApiModelProperty(name = "逻辑删除标识", notes = "(0:未删除，1:已删除)")
    private Integer isDelete;

}