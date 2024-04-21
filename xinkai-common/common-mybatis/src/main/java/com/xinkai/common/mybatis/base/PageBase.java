package com.xinkai.common.mybatis.base;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "页码", example = "1")
    private int pageNum = 1;

    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数", example = "10")
    private int pageSize = 10;
}
