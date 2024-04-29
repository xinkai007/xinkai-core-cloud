package com.xinkai.admin.boot.pojo.from;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author xinkai
 * @className com.xinkai.admin.boot.pojo.from.DictTypeForm
 * @description 字典类型表单
 * @email xinkai8011@gmail.com
 * @date 2024/04/21
 **/
@Data
@ApiModel("字典类型")
public class DictTypeForm {

    @ApiModelProperty("字典类型ID")
    private Long id;

    @ApiModelProperty("类型名称")
    private String name;

    @ApiModelProperty("类型编码")
    private String code;

    @ApiModelProperty("类型状态：1->启用;0->禁用")
    private Integer status;

}
