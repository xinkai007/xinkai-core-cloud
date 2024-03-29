package com.xinkai.admin.boot.pojo.query;

import com.xinkai.common.mybatis.base.PageBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xinkai
 * @className com.xinkai.admin.boot.pojo.query.RoleOptionsQuery
 * @description 角色选择查询
 * @email xinkai8011@gmail.com
 * @date 2023/07/22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleOptionsQuery extends PageBase {
    /**
     * 关键字
     */
    private String keywords;
}
