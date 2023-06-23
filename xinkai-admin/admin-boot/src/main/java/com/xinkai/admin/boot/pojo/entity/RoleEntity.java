package com.xinkai.admin.boot.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.xinkai.common.mybatis.handler.StringArrayJsonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @className: RoleEntity
 * @description: 角色表
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "角色表", description = "")
@TableName(value = "XK_ROLE", autoResultMap = true)
public class RoleEntity extends Model<RoleEntity> implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(name = "主键ID", notes = "")
    @TableId(type = IdType.INPUT)
    private Long id;
    /**
     * 角色名称
     */
    @ApiModelProperty(name = "角色名称", notes = "")
    private String name;
    /**
     * 角色编码
     */
    @ApiModelProperty(name = "角色编码", notes = "")
    @TableField(typeHandler = StringArrayJsonTypeHandler.class)
    private List<String> code;
    /**
     * 显示顺序
     */
    @ApiModelProperty(name = "显示顺序", notes = "")
    private Integer sort;
    /**
     * 角色状态;(1:正常，0:停用)
     */
    @ApiModelProperty(name = "角色状态", notes = "(1:正常，0:停用)")
    private Integer status;
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