package com.xinkai.admin.boot.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author xinkai
 * @className com.xinkai.admin.boot.pojo.vo.MenuVO
 * @description 菜单
 * @email xinkai8011@gmail.com
 * @date 2023/12/01
 **/
@Data
@ApiModel("菜单对象")
public class MenuVO {
    /**
     * ID
     */
    private Long id;

    /**
     * 父ID
     */
    private Long parentId;

    /**
     * 名字
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 路由名称
     */
    private String routeName;

    /**
     * 路由路径
     */
    private String routePath;

    /**
     * 组件
     */
    private String component;

    /**
     * 分类
     */
    private Integer sort;

    /**
     * 可见
     */
    private Integer visible;

    /**
     * 重定向
     */
    private String redirect;

    /**
     * 子菜单
     */
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<MenuVO> children;
}
