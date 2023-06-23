package com.xinkai.auth.security.core.user;

import cn.hutool.core.util.ObjectUtil;
import com.xinkai.admin.api.dto.UserAuthDTO;
import com.xinkai.auth.common.enums.PasswordEncoderTypeEnum;
import com.xinkai.common.core.constant.GlobalConstants;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @className: UserDetails
 * @description: 用户详细信息对象
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/6/22
 **/
@Data
@Slf4j
public class UserDetailsDTO implements UserDetails {

    /**
     * 扩展字段：用户ID
     */
    private Long userId;

    /**
     * 扩展字段：认证身份标识，枚举值如下：
     *
     * @see com.xinkai.common.core.enums.AuthenticationIdentityEnum
     */
    private String authenticationIdentity;

    /**
     * 扩展字段：部门ID
     */
    private Long deptId;

    /**
     * 默认字段
     */
    private String username;
    private String password;
    private Boolean enabled;
    private Collection<SimpleGrantedAuthority> authorities;

    /**
     * 系统管理用户
     */
    public UserDetailsDTO(UserAuthDTO user) {
        log.info("UserDetailsDTO user:{}", user);
        this.setUserId(user.getUserId());
        this.setUsername(user.getUserName());
        this.setDeptId(user.getDeptId());
        this.setPassword(PasswordEncoderTypeEnum.BCRYPT.getPrefix() + user.getPassword());
        this.setEnabled(GlobalConstants.STATUS_YES.equals(user.getStatus()));
        if (ObjectUtil.isNotEmpty(user.getRoles())) {
            authorities = new ArrayList<>();
            user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        }
        log.info("UserDetailsDTO There are parameter constructors:{}", this);
    }


    /**
     * 得到当局
     *
     * @return {@link Collection}<{@link ?} {@link extends} {@link GrantedAuthority}>
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    /**
     * 得到密码
     *
     * @return {@link String}
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * 获得用户名
     *
     * @return {@link String}
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * 账户是否过期(true:未过期，false：已过期)
     *
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否锁定(true:未锁定，false：已锁定)
     *
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 凭证是否过期(true:未过期，false：已过期)
     *
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户是否启用
     *
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
