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
 * @className: AuthClientEntity
 * @description: 鉴权客户端表
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "鉴权客户端表", description = "")
@TableName("XK_AUTH_CLIENT")
public class AuthClientEntity extends Model<AuthClientEntity> implements Serializable {
    /**
     * 客户端ID
     */
    @ApiModelProperty(name = "客户端ID", notes = "")
    @TableId(type = IdType.INPUT)
    private String clientId;
    /**
     * 客户端密钥
     */
    @ApiModelProperty(name = "客户端密钥", notes = "")
    private String resourceIds;
    /**
     * 资源id集合
     */
    @ApiModelProperty(name = "资源id集合", notes = "")
    private String clientSecret;
    /**
     * 作用域
     */
    @ApiModelProperty(name = "作用域", notes = "")
    private String scope;
    /**
     * 授权方式
     */
    @ApiModelProperty(name = "授权方式", notes = "")
    private String authorizedGrantTypes;
    /**
     * 回调地址
     */
    @ApiModelProperty(name = "回调地址", notes = "")
    private String webServerRedirectUri;
    /**
     * 权限集合
     */
    @ApiModelProperty(name = "权限集合", notes = "")
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
    @ApiModelProperty(name = "扩展信息", notes = "")
    private String additionalInformation;
    /**
     * 是否自动放行;(0:是，1:否)
     */
    @ApiModelProperty(name = "是否自动放行", notes = "(0:是，1:否)")
    private Integer autoApprove;
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