package com.xinkai.common.mybatis.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * @className: Option
 * @description: 选项值
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/6/26
 **/
@Data
public class Option<T> {

    public Option(T value, String label) {
        this.value = value;
        this.label = label;
    }

    public Option(T value, String label, List<Option<T>> children) {
        this.value = value;
        this.label = label;
        this.children = children;
    }

    /**
     * 选项的值
     */
    @ApiModelProperty("选项的值")
    @Getter
    private T value;

    /**
     * 选项的标签
     */
    @ApiModelProperty("选项的标签")
    @Getter
    private String label;

    /**
     * 子列表
     */
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private List<Option<T>> children;
}
