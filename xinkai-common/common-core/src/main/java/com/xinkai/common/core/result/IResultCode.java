package com.xinkai.common.core.result;

/**
 * @className: IResultCode
 * @description: 结果处理接口
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2022/6/4
 **/
public interface IResultCode {
    /**
     * 获取代码
     *
     * @return {@link String}
     */
    String getCode();

    /**
     * 获取信息
     *
     * @return {@link String}
     */
    String getMsg();
}
