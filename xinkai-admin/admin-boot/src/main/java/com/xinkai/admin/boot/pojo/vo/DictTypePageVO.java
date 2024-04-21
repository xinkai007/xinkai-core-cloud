package com.xinkai.admin.boot.pojo.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@ApiModel("字典类型")
@Accessors(chain = true)
public class DictTypePageVO {

    @ApiModelProperty("字典类型ID")
    private Long id;

    @ApiModelProperty("类型名称")
    private String name;

    @ApiModelProperty("类型编码")
    private String code;

    @ApiModelProperty("类型状态：1->启用;0->禁用")
    private Integer status;

}
