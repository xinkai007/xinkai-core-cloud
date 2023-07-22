package com.xinkai.admin.boot.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xinkai
 * @className com.xinkai.admin.boot.pojo.dto.DeptInfoDTO
 * @description 部门信息dto
 * @email xinkai8011@gmail.com
 * @date 2023/07/19
 **/
@Data
@Accessors(chain = true)
public class DeptInfoDTO {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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
     * 排序
     */
    private Integer sort;
    /**
     * 状态
     */
    private Integer status;
}
