package com.xinkai.admin.boot.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xinkai
 * @className RoleDTO
 * @description 角色修改请求参数
 * @email xinkai8011@gmail.com
 * @date 2023/9/10
 **/
@Data
@Accessors(chain = true)
public class RoleDTO {
    /**
     * ID
     */
    private Long id;
    /**
     * 名字
     */
    private String name;
    /**
     * 编码
     */
    private String code;
    /**
     * 分类
     */
    private Integer sort;
    /**
     * 状态
     */
    private Integer status;
}
