package com.xinkai.common.mybatis.base;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @className: PageBase
 * @description: 分页查询基础参数
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/6/25
 **/
@Data
@Accessors(chain = true)
public class PageBase {
    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 页面大小
     */
    private Integer pageSize;
}
