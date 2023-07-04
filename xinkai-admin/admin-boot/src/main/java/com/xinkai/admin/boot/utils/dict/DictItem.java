package com.xinkai.admin.boot.utils.dict;

import lombok.Data;

/**
 * @className: DictItem
 * @description: 词典列表
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/6/26
 **/
@Data
public class DictItem {
    /**
     * 代码
     */
    private String code;
    /**
     * 值
     */
    private String value;
    /**
     * 名字
     */
    private String name;
}
