package com.xinkai.admin.boot.pojo.query;

import com.xinkai.common.mybatis.base.PageBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xinkai
 * @className com.xinkai.admin.boot.pojo.query.RoleListQuery
 * @description 角色列表查询
 * @email xinkai8011@gmail.com
 * @date 2023/07/22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleListQuery extends PageBase {

    /**
     * 关键字
     */
    @ApiModelProperty(name = "关键字")
    private String keywords;
}
