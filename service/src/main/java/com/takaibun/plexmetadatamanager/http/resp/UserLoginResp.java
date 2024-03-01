package com.takaibun.plexmetadatamanager.http.resp;

import lombok.Data;

/**
 * 用户登陆响应体
 *
 * @author takaibun
 * @since 2024/03/02
 */
@Data
public class UserLoginResp {
    private String token;
}
