package com.xinkai.admin.boot.pojo.query;

import lombok.Data;

/**
 * @author xinkai
 * @className com.xinkai.admin.boot.pojo.query.DeptListQuery
 * @description 部门列表查询
 * @email xinkai8011@gmail.com
 * @date 2023/07/16
 **/
@Data
public class DeptListQuery {
    /**
     * 名字
     */
    private String name;
    /**
     * 部门状态 (1:正常，0:禁用)
     */
    private Integer status;
}
