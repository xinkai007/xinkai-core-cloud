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
 * @className AuthClientEntity
 * @description 鉴权客户端表
 * @email xinkai8011@gmail.com
 * @date 2023-7-22
 **/
@Data
@TableName("XK_AUTH_CLIENT")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "鉴权客户端表", description = "鉴权客户端表实体类")
public class AuthClientEntity extends Model<AuthClientEntity> implements Serializable {
    /**
     * 客户端ID
     */
    @ApiModelProperty(name = "客户端ID")
    @TableId(type = IdType.INPUT)
    private String clientId;
    /**
     * 客户端密钥
     */
    @ApiModelProperty(name = "客户端密钥")
    private String resourceIds;
    /**
     * 资源id集合
     */
    @ApiModelProperty(name = "资源id集合")
    private String clientSecret;
    /**
     * 作用域
     */
    @ApiModelProperty(name = "作用域")
    private String scope;
    /**
     * 授权方式
     */
    @ApiModelProperty(name = "授权方式")
    private String authorizedGrantTypes;
    /**
     * 回调地址
     */
    @ApiModelProperty(name = "回调地址")
    private String webServerRedirectUri;
    /**
     * 权限集合
     */
    @ApiModelProperty(name = "权限集合")
    private String authorities;
    /**
     * 认证令牌时效;(单位:秒)
     */
    @ApiModelProperty(name = "认证令牌时效", notes = "(单位:秒)")
    private Integer accessTokenValidity;
    /**
     * 刷新令牌时效;(单位:秒)
     */
    @ApiModelProperty(name = "刷新令牌时效", notes = "(单位:秒)")
    private Integer refreshTokenValidity;
    /**
     * 扩展信息
     */
    @ApiModelProperty(name = "扩展信息")
    private String additionalInformation;
    /**
     * 是否自动放行;(0:是，1:否)
     */
    @ApiModelProperty(name = "是否自动放行", notes = "(0:是，1:否)")
    private Integer autoApprove;
    /**
     * 创建人
     */
    @ApiModelProperty(name = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createUser;
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
    private String updateUser;
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