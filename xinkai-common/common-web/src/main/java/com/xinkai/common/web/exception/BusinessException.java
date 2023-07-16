package com.xinkai.common.web.exception;

import com.xinkai.common.core.result.IResultCode;
import lombok.Getter;

/**
 * @author xinkai
 * @className com.xinkai.common.web.exception.BusinessException
 * @description 业务异常
 * @email xinkai8011@gmail.com
 * @date 2023/07/14
 **/
@Getter
public class BusinessException extends RuntimeException {
    public IResultCode resultCode;

    public BusinessException(IResultCode errorCode) {
        super(errorCode.getMsg());
        this.resultCode = errorCode;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

}
