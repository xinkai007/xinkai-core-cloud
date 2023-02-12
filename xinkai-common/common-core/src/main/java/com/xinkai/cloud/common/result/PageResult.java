package com.xinkai.cloud.common.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @className: PageResult
 * @description: 分页响应结构体，泛型类。
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2022/6/4
 **/
@Data
public class PageResult<T> implements Serializable {

    private String code;

    private String msg;

    private T data;

    public static <T> PageResult<T> success(IPage<T> page) {
        PageResult result = new PageResult<T>();
        result.setCode(ResultCode.SUCCESS.getCode());

        Data data = new Data<T>();
        data.setList(page.getRecords());
        data.setTotal(page.getTotal());

        result.setData(data);
        result.setMsg(ResultCode.SUCCESS.getMsg());
        return result;
    }

    @lombok.Data
    public static class Data<T> {

        private List<T> list;

        private long total;

    }


}
