package com.xinkai.admin.boot.utils.dict;

import com.xinkai.common.core.base.IBaseEnum;

/**
 * @className: DictEnums
 * @description:
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/7/3
 **/
public enum DictEnums implements IBaseEnum<String> {
    GENDER("GENDER", "性别"),

    DEPT("DEPT", "部门");

    private String value;
    private String label;

    DictEnums(String value, String label) {
        this.value = value;
        this.label = label;
    }

    /**
     * 获取值
     *
     * @return {@link String}
     */
    @Override
    public String getValue() {
        return this.value;
    }

    /**
     * 获取标签
     *
     * @return {@link String}
     */
    @Override
    public String getLabel() {
        return this.label;
    }
}
