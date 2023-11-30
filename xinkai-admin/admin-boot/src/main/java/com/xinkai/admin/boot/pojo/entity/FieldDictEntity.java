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
 * @className FieldDictEntity
 * @description 栏位词典表
 * @email xinkai8011@gmail.com
 * @date 2023-7-22
 **/
@Data
@TableName("XK_FIELD_DICT")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "栏位词典表", description = "栏位词典表实体类")
public class FieldDictEntity extends Model<FieldDictEntity> implements Serializable {
    /**
     * ID
     */
    @ApiModelProperty(name = "ID")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 名称
     */
    @ApiModelProperty(name = "名称")
    private String name;
    /**
     * 备注
     */
    @ApiModelProperty(name = "备注")
    private String common;
    /**
     * 类型
     */
    @ApiModelProperty(name = "类型")
    private Integer type;
    /**
     * 长度
     */
    @ApiModelProperty(name = "长度")
    private Integer length;
    /**
     * 是否为空;(0：是，1：否)
     */
    @ApiModelProperty(name = "是否为空", notes = "(0：是，1：否)")
    private Integer isNull;
    /**
     * 默认值
     */
    @ApiModelProperty(name = "默认值")
    private String defaultValue;
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