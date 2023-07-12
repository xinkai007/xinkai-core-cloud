package com.xinkai.admin.boot.pojo.query;

import com.xinkai.common.mybatis.base.PageBase;
import lombok.Data;

/**
 * @className: RoleOptionsQuery
 * @description: 角色列表查询
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/7/6
 **/
@Data
public class RoleOptionsQuery extends PageBase {
    private String name;
}
