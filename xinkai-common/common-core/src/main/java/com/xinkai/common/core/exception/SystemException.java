package com.xinkai.common.core.exception;

import com.xinkai.common.core.result.ResultCode;

/**
 * @className: SystemException
 * @description:
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/5/24
 **/
public class SystemException extends Exception {
    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(ResultCode resultCode) {
        super(resultCode.getMsg());
    }

}
