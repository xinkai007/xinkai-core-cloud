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
 * @className: DeptEntity
 * @description: 部门表
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "部门表", description = "")
@TableName("XK_DEPT")
public class DeptEntity extends Model<DeptEntity> implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(name = "主键", notes = "")
    @TableId(type = IdType.INPUT)
    private Long id;
    /**
     * 部门名称
     */
    @ApiModelProperty(name = "部门名称", notes = "")
    private String name;
    /**
     * 父节点id
     */
    @ApiModelProperty(name = "父节点id", notes = "")
    private Long parentId;
    /**
     * 父节点id路径
     */
    @ApiModelProperty(name = "父节点id路径", notes = "")
    private String treePath;
    /**
     * 显示顺序
     */
    @ApiModelProperty(name = "显示顺序", notes = "")
    private Integer sort;
    /**
     * 状态;(1:正常，0:禁用)
     */
    @ApiModelProperty(name = "状态", notes = "(1:正常，0:禁用)")
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