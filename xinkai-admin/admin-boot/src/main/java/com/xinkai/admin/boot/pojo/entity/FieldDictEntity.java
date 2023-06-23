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
 * @className: FieldDictEntity
 * @description: 栏位词典表
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "栏位词典表", description = "")
@TableName("XK_FIELD_DICT")
public class FieldDictEntity extends Model<FieldDictEntity> implements Serializable {
    /**
     * ID
     */
    @ApiModelProperty(name = "ID", notes = "")
    @TableId(type = IdType.INPUT)
    private Long id;
    /**
     * 名称
     */
    @ApiModelProperty(name = "名称", notes = "")
    private String name;
    /**
     * 备注
     */
    @ApiModelProperty(name = "备注", notes = "")
    private String common;
    /**
     * 类型
     */
    @ApiModelProperty(name = "类型", notes = "")
    private Integer type;
    /**
     * 长度
     */
    @ApiModelProperty(name = "长度", notes = "")
    private Integer length;
    /**
     * 是否为空;(0：是，1：否)
     */
    @ApiModelProperty(name = "是否为空", notes = "(0：是，1：否)")
    private Integer isNull;
    /**
     * 默认值
     */
    @ApiModelProperty(name = "默认值", notes = "")
    private String defaultValue;
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