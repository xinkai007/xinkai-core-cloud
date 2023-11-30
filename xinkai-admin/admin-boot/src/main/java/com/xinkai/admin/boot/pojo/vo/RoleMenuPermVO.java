package com.xinkai.admin.boot.pojo.vo;

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
public class RoleMenuPermVO {
    /**
     * 菜单ID集合
     */
    @ApiModelProperty("菜单ID集合")
    private List<Long> menuIds;

    /**
     * 权限ID集合
     */
    @ApiModelProperty("权限ID集合")
    private List<Long> permIds;
}
