package com.xinkai.admin.boot.pojo.query;


import com.xinkai.common.mybatis.base.PageBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@ApiModel("字典数据项分页查询对象")
@EqualsAndHashCode(callSuper = true)
public class DictItemPageQuery extends PageBase {

    @ApiModelProperty("关键字(字典项名称)")
    private String keywords;

    @ApiModelProperty("字典类型编码")
    private String typeCode;
}
