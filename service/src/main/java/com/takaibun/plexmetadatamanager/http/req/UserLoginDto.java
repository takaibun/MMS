package com.takaibun.plexmetadatamanager.http.req;

import lombok.Data;

/**
 * 用户登录请求
 *
 * @author takaibun
 * @since 2023/02/24
 */
@Data
public class UserLoginDto {
    private String username;
    private String password;
}
