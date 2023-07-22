package com.xinkai.admin.boot.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author xinkai
 * @className com.xinkai.admin.boot.pojo.vo.DeptListVO
 * @description 部门列表签证官
 * @email xinkai8011@gmail.com
 * @date 2023/07/16
 **/
@Data
public class DeptListVO {
    /**
     * id
     */
    private Long id;
    /**
     * 名字
     */
    private String name;
    /**
     * 父id
     */
    private Long parentId;
    /**
     * 树路径
     */
    private String treePath;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 领袖
     */
    private String leader;
    /**
     * 移动
     */
    private String mobile;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * 下级部门
     */
    private List<DeptListVO> children;
}
