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
 * @className: DictItemEntity
 * @description: 字典数据表
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "字典数据表", description = "")
@TableName("XK_DICT_ITEM")
public class DictItemEntity extends Model<DictItemEntity> implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(name = "主键", notes = "")
    @TableId(type = IdType.INPUT)
    private Long id;
    /**
     * 字典类型编码
     */
    @ApiModelProperty(name = "字典类型编码", notes = "")
    private String typeCode;
    /**
     * 字典项名称
     */
    @ApiModelProperty(name = "字典项名称", notes = "")
    private String name;
    /**
     * 字典项值
     */
    @ApiModelProperty(name = "字典项值", notes = "")
    private String value;
    /**
     * 排序
     */
    @ApiModelProperty(name = "排序", notes = "")
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
    @ApiModelProperty(name = "备注", notes = "")
    private String remark;
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