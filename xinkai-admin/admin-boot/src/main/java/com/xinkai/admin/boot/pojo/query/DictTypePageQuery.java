package com.xinkai.admin.boot.pojo.query;


import com.xinkai.common.mybatis.base.PageBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author xinkai
 * @className com.xinkai.admin.boot.pojo.query.DictTypePageQuery
 * @description dict类型页面查询
 * @email xinkai8011@gmail.com
 * @date 2024/04/21
 **/


@Data
@ApiModel("字典类型分页查询对象")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DictTypePageQuery extends PageBase {

    @ApiModelProperty("关键字(类型名称/类型编码)")
    private String keywords;

}
