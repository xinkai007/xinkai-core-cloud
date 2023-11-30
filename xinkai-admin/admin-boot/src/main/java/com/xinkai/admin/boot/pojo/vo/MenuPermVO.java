package com.xinkai.admin.boot.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author xinkai
 * @className com.xinkai.admin.boot.pojo.vo.MenuVO
 * @description 菜单权限对象
 * @email xinkai8011@gmail.com
 * @date 2023/11/30
 **/
@Data
@ApiModel("菜单权限对象")
public class MenuPermVO {

    @ApiModelProperty("菜单列表")
    private List<MenuOption> menus;

    @ApiModelProperty("按钮权限列表")
    private List<PermOption> perms;

    /**
     * @author xinkai
     * @className com.xinkai.admin.boot.pojo.vo.MenuVO.MenuOption
     * @description 菜单选项
     * @email xinkai8011@gmail.com
     * @date 2023/07/22
     **/
    @Data
    public static class MenuOption {
        /**
         * 值
         */
        @ApiModelProperty("选项的值")
        private Long value;
        /**
         * 标签
         */
        @ApiModelProperty("选项的标签")
        private String label;
        /**
         * 禁用
         */
        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        @ApiModelProperty("是否禁用该选项，默认false")
        public Boolean disabled;
        /**
         * 子数据
         */
        @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
        private List<MenuOption> children;
        /**
         * 权限
         */
        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        private Boolean isPerm;
        /**
         * 权限pid
         */
        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        private Long permPid;

    }


    /**
     * @author xinkai
     * @className com.xinkai.admin.boot.pojo.vo.MenuVO.PermOption
     * @description 权限选择
     * @email xinkai8011@gmail.com
     * @date 2023/07/22
     **/
    @Data
    @AllArgsConstructor
    public static class PermOption {
        /**
         * 父id
         */
        @ApiModelProperty("父ID")
        private Long parentId;
        /**
         * 价值
         */
        @ApiModelProperty("选项的值")
        private Long value;
        /**
         * 标签
         */
        @ApiModelProperty("选项的标签")
        private String label;

    }
}
