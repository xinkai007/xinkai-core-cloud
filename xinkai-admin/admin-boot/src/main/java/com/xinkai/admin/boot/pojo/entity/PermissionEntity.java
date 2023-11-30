package com.xinkai.admin.boot.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
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
 * @className PermissionEntity
 * @description 权限表
 * @email xinkai8011@gmail.com
 * @date 2023-7-22
 **/
@Data
@TableName("XK_PERMISSION")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "权限表", description = "权限表实体类")
public class PermissionEntity extends Model<PermissionEntity> implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(name = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 权限名称
     */
    @ApiModelProperty(name = "权限名称")
    private String name;
    /**
     * 菜单模块ID
     */
    @ApiModelProperty(name = "菜单模块ID")
    private Long menuId;
    /**
     * URL权限标识
     */
    @ApiModelProperty(name = "URL权限标识")
    private String urlPerm;
    /**
     * 按钮权限标识
     */
    @ApiModelProperty(name = "按钮权限标识")
    private String btnPerm;
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
    @TableLogic(value = "0", delval = "1")
    private Integer isDelete;

}