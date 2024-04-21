package com.xinkai.common.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.xinkai.common.core.base.IBaseEnum;
import lombok.Getter;

/**
 * @author xinkai
 * @className com.xinkai.common.core.enums.MenuTypeEnum
 * @description 菜单类型列举
 * @email xinkai8011@gmail.com
 * @date 2024/04/20
 **/
public enum MenuTypeEnum implements IBaseEnum<Integer> {

    NULL(0, null),
    MENU(1, "菜单"),
    CATALOG(2, "目录"),
    EXT_LINK(3, "外链");


    /**
     * Mybatis-Plus 提供注解表示插入数据库时插入该值
     */
    @Getter
    @EnumValue
    private final Integer value;
    /**
     * @JsonValue 表示对枚举序列化时返回此字段
     */
    @Getter
    private final String label;

    MenuTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
