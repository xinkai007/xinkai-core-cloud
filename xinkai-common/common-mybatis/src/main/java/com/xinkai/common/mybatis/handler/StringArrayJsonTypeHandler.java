package com.xinkai.common.mybatis.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.stereotype.Component;

/**
 * @className: com.xinkai.common.mybatis.handler.StringArrayJsonTypeHandler
 * @description: String 数组类型转换 json
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/06/23
 **/
@Slf4j
@Component
@MappedTypes(value = {String[].class})
@MappedJdbcTypes(value = {JdbcType.OTHER}, includeNullJdbcType = true)
public class StringArrayJsonTypeHandler extends ArrayObjectJsonTypeHandler<String> {
    public StringArrayJsonTypeHandler() {
        super(String[].class);
    }
}
