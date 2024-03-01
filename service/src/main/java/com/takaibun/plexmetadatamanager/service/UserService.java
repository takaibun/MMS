package com.takaibun.plexmetadatamanager.service;

import com.takaibun.plexmetadatamanager.http.req.UserLoginDto;
import com.takaibun.plexmetadatamanager.http.req.UserUpdateDto;
import com.takaibun.plexmetadatamanager.http.resp.UserLoginResp;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 用户服务
 *
 * @author takaibun
 * @since 2024/02/24
 */
public interface UserService extends UserDetailsService {
    /**
     * 登录
     *
     * @param userLoginDto 用户登录信息
     * @return 登陆响应
     */
    UserLoginResp login(UserLoginDto userLoginDto);

    /**
     * 退出登录
     */
    void logout();

    /**
     * 更新密码
     *
     * @param userUpdateDto 用户更新密码信息
     */
    void update(UserUpdateDto userUpdateDto);

    /**
     * 验证token
     *
     * @param token token
     * @return
     */
    boolean validateToken(String token);

    /**
     * 刷新token
     *
     * @param token token
     * @return new token
     */
    String refresh(String token);
}
