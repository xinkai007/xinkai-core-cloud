package com.xinkai.admin.boot.pojo.query;

import com.xinkai.common.mybatis.base.PageBase;
import lombok.Data;

/**
 * @className: UserListQuery
 * @description: 用户列表查询请求参数对象
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/6/25
 **/
@Data
public class UserListQuery extends PageBase {

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 状态(-1:全部，1:正常，0:禁用)
     */
    private Integer status;

    /**
     * 部门
     */
    private Integer deptId;
}
