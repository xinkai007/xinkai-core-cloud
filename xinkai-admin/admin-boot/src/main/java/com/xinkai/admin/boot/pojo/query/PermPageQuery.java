package com.xinkai.admin.boot.pojo.query;

import com.xinkai.common.mybatis.base.PageBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限分页查询对象
 *
 * @author xinkai
 * @date 2022/1/14 22:22
 */
@Data
@ApiModel
@EqualsAndHashCode(callSuper = true)
public class PermPageQuery extends PageBase {

    @ApiModelProperty("权限名称")
    private String name;

    @ApiModelProperty("菜单ID")
    private Long menuId;

}
