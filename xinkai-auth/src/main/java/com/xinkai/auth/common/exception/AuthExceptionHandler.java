package com.xinkai.auth.common.exception;

import com.xinkai.common.core.result.Result;
import com.xinkai.common.core.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @className: AuthExceptionHandler
 * @description: 鉴权服务异常处理器
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/6/20
 **/
@Slf4j
@RestControllerAdvice
public class AuthExceptionHandler {

    /**
     * 用户不存在
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    public Result handleUsernameNotFoundException(UsernameNotFoundException e) {
        return Result.failed(ResultCode.USER_NOT_EXIST);
    }

    /**
     * 用户名和密码异常
     *
     * @param e
     * @return {@link Result}
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidGrantException.class)
    public Result<String> handleInvalidGrantException(InvalidGrantException e) {
        return Result.failed(ResultCode.USERNAME_OR_PASSWORD_ERROR);
    }


    /**
     * 账户异常(禁用、锁定、过期)
     *
     * @param e
     * @return {@link Result}
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InternalAuthenticationServiceException.class})
    public Result<String> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
        return Result.failed(e.getMessage());
    }

    /**
     * token 无效或已过期
     *
     * @param e
     * @return {@link Result}
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidTokenException.class})
    public Result<String> handleInvalidTokenException(InvalidTokenException e) {
        return Result.failed(e.getMessage());
    }
}
