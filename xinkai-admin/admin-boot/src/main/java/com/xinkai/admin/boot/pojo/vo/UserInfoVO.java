package com.xinkai.admin.boot.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @className: com.xinkai.admin.boot.pojo.vo.UserInfoVO
 * @description: 登录用户视图层对象
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/06/23
 **/
@Data
@ApiModel("当前登录用户视图对象")
public class UserInfoVO {

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("头像地址")
    private String avatar;

    @ApiModelProperty("用户的角色编码集合")
    private List<String> roles;

    @ApiModelProperty("用户的按钮权限标识集合")
    private List<String> perms;
}
