package com.xinkai.admin.boot.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xinkai
 * @className UserRoleEntity
 * @description 用户和角色关联表
 * @email xinkai8011@gmail.com
 * @date 2023-7-22
 **/
@Data
@TableName("XK_USER_ROLE")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户和角色关联表", description = "用户和角色关联表实体类")
public class UserRoleEntity extends Model<UserRoleEntity> implements Serializable {
    /**
     * 用户ID
     */
    @ApiModelProperty(name = "用户ID")
    private Long userId;
    /**
     * 角色ID
     */
    @ApiModelProperty(name = "角色ID")
    private Long roleId;
    /**
     * 创建人
     */
    @ApiModelProperty(name = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;
    /**
     * 创建时间
     */
    @ApiModelProperty(name = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 更新人
     */
    @ApiModelProperty(name = "更新人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
    /**
     * 更新时间
     */
    @ApiModelProperty(name = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    /**
     * 逻辑删除标识;(0:未删除，1:已删除)
     */
    @ApiModelProperty(name = "逻辑删除标识", notes = "(0:未删除，1:已删除)")
    private Integer isDelete;

}