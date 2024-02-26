package com.takaibun.plexmetadatamanager.service;

import com.takaibun.plexmetadatamanager.http.req.UserLoginDto;
import com.takaibun.plexmetadatamanager.http.req.UserUpdateDto;
import com.takaibun.plexmetadatamanager.http.resp.UserLoginResp;

/**
 * 用户服务
 *
 * @author takaibun
 * @since 2024/02/24
 */
public interface UserService {
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
     */
    void validateToken(String token);

    /**
     * 刷新token
     *
     * @return token
     */
    UserLoginResp refreshToken();
}
