package com.xinkai.admin.boot.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author xinkai
 * @className com.xinkai.admin.boot.pojo.vo.RoleInfoVO
 * @description 签证官角色信息
 * @email xinkai8011@gmail.com
 * @date 2023/07/22
 **/
@Data
@Accessors(chain = true)
public class RoleInfoVO {
    /**
     * id
     */
    @ApiModelProperty(name = "角色ID")
    private Long id;
    /**
     * 角色名称
     */
    @ApiModelProperty(name = "角色名称")
    private String name;
    /**
     * 角色编码
     */
    @ApiModelProperty(name = "角色编码")
    private String code;
    /**
     * 排序
     */
    @ApiModelProperty(name = "排序")
    private Integer sort;
    /**
     * 状态
     */
    @ApiModelProperty(name = "状态")
    private Integer status;
    /**
     * 删除
     */
    @ApiModelProperty(name = "删除")
    private Integer deleted;
    /**
     * 菜单id
     */
    @ApiModelProperty(name = "菜单id")
    private List<Long> menuIds;
    /**
     * 授权id
     */
    @ApiModelProperty(name = "授权id")
    private List<Long> permissionIds;
}
