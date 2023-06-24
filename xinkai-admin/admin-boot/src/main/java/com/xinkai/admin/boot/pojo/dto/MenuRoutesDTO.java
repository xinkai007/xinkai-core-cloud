package com.xinkai.admin.boot.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @className: MenuRoutDTO
 * @description: 菜单路由地址信息
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/6/24
 **/
@Data
public class MenuRoutesDTO {
    /**
     * 菜单id
     */
    private Long id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 父菜单ID
     */
    private Long parentId;
    /**
     * 路由路径(浏览器地址栏路径)
     */
    private String path;
    /**
     * 组件路径(vue页面完整路径，省略.vue后缀)
     */
    private String component;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态;(0:禁用，1:开启)
     */
    private Integer visible;
    /**
     * 跳转路径
     */
    private String redirect;
    /**
     * 角色
     */
    private List<String> roles;
    /**
     * 菜单类型;(1:菜单，2:目录
     */
    private Integer type;

    /**
     * @className: com.xinkai.admin.boot.pojo.dto.MenuRoutesDTO.MetaVO
     * @description: 元数据对象
     * @author: xinkai
     * @email: xinkai8011@gmail.com
     * @date: 2023/06/24
     **/
    @Data
    @Accessors(chain = true)
    public static class Meta {
        /**
         * 标题
         */
        private String title;
        /**
         * 图标
         */
        private String icon;
        /**
         * 隐藏
         */
        private Boolean hidden;
        /**
         * 如果设置为 true，目录没有子节点也会显示
         */
        private Boolean alwaysShow;
        /**
         * 角色
         */
        private List<String> roles;
        /**
         * 页面缓存开启状态
         */
        private Boolean keepAlive;
    }
}
