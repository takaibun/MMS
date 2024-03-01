package com.takaibun.plexmetadatamanager.entity;

import java.io.Serial;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 用户实体
 *
 * @author takaibun
 * @since 2024/02/24
 */
@Data
@TableName(value = "t_msm_user", autoResultMap = true)
public class UserEntity implements UserDetails {
    @Serial
    private static final long serialVersionUID = 4625932258763942837L;
    private String username;

    private String password;

    private String apiKey;

    private Date createdAt;

    private Date updatedAt;

    @TableField(exist = false)
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
