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
 * @className DictItemEntity
 * @description 字典数据表
 * @email xinkai8011@gmail.com
 * @date 2023-7-22
 **/
@Data
@TableName("XK_DICT_ITEM")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "字典数据表", description = "字典数据表实体类")
public class DictItemEntity extends Model<DictItemEntity> implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(name = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 字典类型编码
     */
    @ApiModelProperty(name = "字典类型编码")
    private String typeCode;
    /**
     * 字典项名称
     */
    @ApiModelProperty(name = "字典项名称")
    private String name;
    /**
     * 字典项值
     */
    @ApiModelProperty(name = "字典项值")
    private String value;
    /**
     * 排序
     */
    @ApiModelProperty(name = "排序")
    private Integer sort;
    /**
     * 状态;(1:正常，0:禁用)
     */
    @ApiModelProperty(name = "状态", notes = "(1:正常，0:禁用)")
    private Integer status;
    /**
     * 是否默认;(1:是，0:否)
     */
    @ApiModelProperty(name = "是否默认", notes = "(1:是，0:否)")
    private Integer defaulted;
    /**
     * 备注
     */
    @ApiModelProperty(name = "备注")
    private String remark;
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