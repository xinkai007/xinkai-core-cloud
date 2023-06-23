package com.xinkai.admin.boot.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @className: PermissionEntity
 * @description: 权限表
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "权限表", description = "")
@TableName("XK_PERMISSION")
public class PermissionEntity extends Model<PermissionEntity> implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(name = "主键", notes = "")
    @TableId(type = IdType.INPUT)
    private Long id;
    /**
     * 权限名称
     */
    @ApiModelProperty(name = "权限名称", notes = "")
    private String name;
    /**
     * 菜单模块ID
     */
    @ApiModelProperty(name = "菜单模块ID", notes = "")
    private Long menuId;
    /**
     * URL权限标识
     */
    @ApiModelProperty(name = "URL权限标识", notes = "")
    private String urlPerm;
    /**
     * 按钮权限标识
     */
    @ApiModelProperty(name = "按钮权限标识", notes = "")
    private String btnPerm;
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