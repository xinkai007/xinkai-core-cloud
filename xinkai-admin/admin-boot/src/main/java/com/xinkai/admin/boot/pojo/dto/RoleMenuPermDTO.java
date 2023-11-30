package com.xinkai.admin.boot.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author xinkai
 * @className com.xinkai.admin.boot.pojo.vo.RoleMenuPermVO
 * @description 角色菜单烫发
 * @email xinkai8011@gmail.com
 * @date 2023/11/24
 **/
@Data
@Accessors(chain = true)
public class RoleMenuPermDTO {
    /**
     * 菜单ID
     */
    @ApiModelProperty(name = "菜单ID")
    private List<Long> menuIds;

    /**
     * 权限ID
     */
    @ApiModelProperty(name = "权限Id")
    private List<Long> permIds;
}
