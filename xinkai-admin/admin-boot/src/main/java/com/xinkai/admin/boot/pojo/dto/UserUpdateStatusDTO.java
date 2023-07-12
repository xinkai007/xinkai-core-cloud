package com.xinkai.admin.boot.pojo.dto;

import lombok.Data;

/**
 * @className: UserUpdateStatusDTO
 * @description: 修改用户状态传输数据
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/7/5
 **/
@Data
public class UserUpdateStatusDTO {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户状态 (1:正常，0:禁用)
     */

    private Integer status;
}
