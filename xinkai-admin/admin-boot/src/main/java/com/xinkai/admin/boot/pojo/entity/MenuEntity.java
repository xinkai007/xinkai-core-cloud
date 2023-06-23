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
 * @className: MenuEntity
 * @description: 菜单管理
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "菜单管理", description = "")
@TableName("XK_MENU")
public class MenuEntity extends Model<MenuEntity> implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(name = "主键ID", notes = "")
    @TableId(type = IdType.INPUT)
    private Long id;
    /**
     * 父菜单ID
     */
    @ApiModelProperty(name = "父菜单ID", notes = "")
    private Long parentId;
    /**
     * 菜单名称
     */
    @ApiModelProperty(name = "菜单名称", notes = "")
    private String name;
    /**
     * 路由路径(浏览器地址栏路径)
     */
    @ApiModelProperty(name = "路由路径(浏览器地址栏路径)", notes = "")
    private String path;
    /**
     * 组件路径(vue页面完整路径，省略.vue后缀)
     */
    @ApiModelProperty(name = "组件路径(vue页面完整路径，省略.vue后缀)", notes = "")
    private String component;
    /**
     * 菜单图标
     */
    @ApiModelProperty(name = "菜单图标", notes = "")
    private String icon;
    /**
     * 排序
     */
    @ApiModelProperty(name = "排序", notes = "")
    private Integer sort;
    /**
     * 状态;(0:禁用，1:开启)
     */
    @ApiModelProperty(name = "状态", notes = "(0:禁用，1:开启)")
    private Integer visible;
    /**
     * 跳转路径
     */
    @ApiModelProperty(name = "跳转路径", notes = "")
    private String redirect;
    /**
     * 菜单类型;(1:菜单，2:目录
     */
    @ApiModelProperty(name = "菜单类型", notes = "(1:菜单，2:目录")
    private Integer type;
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