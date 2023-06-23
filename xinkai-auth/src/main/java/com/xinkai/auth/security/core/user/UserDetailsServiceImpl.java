package com.xinkai.auth.security.core.user;

import com.xinkai.admin.api.dto.UserAuthDTO;
import com.xinkai.admin.api.feign.AdminFeign;
import com.xinkai.common.core.result.Result;
import com.xinkai.common.core.result.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @className: UserDetailsServiceImpl
 * @description: 利用 UserDetailsService 接口从数据库中获取用户信息,
 * 并通过实现 AuthenticationProvider 接口编写自己的校验逻辑,
 * 从而完成 SpringSecurity 身份校验。
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/6/22
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AdminFeign adminFeign;

    /**
     * 根据用户名称加载用户信息
     *
     * @param username 用户名
     * @return {@link UserDetails}
     * @throws UsernameNotFoundException 用户名没有发现异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsDTO userDetails = null;
        Result<UserAuthDTO> result = adminFeign.getUserByUserName(username);
        if (Result.isSuccess(result)) {
            UserAuthDTO user = result.getData();
            if (null != user) {
                userDetails = new UserDetailsDTO(user);
            }
        }
        if (userDetails == null) {
            throw new UsernameNotFoundException(ResultCode.USER_NOT_EXIST.getMsg());
        } else if (!userDetails.isEnabled()) {
            throw new DisabledException("该账户已被禁用!");
        } else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定!");
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期!");
        }
        return userDetails;
    }
}
