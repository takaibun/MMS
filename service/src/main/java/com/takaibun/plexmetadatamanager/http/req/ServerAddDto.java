package com.takaibun.plexmetadatamanager.http.req;

import com.takaibun.plexmetadatamanager.enums.ServerSubType;
import com.takaibun.plexmetadatamanager.enums.ServerType;

import lombok.Data;

/**
 * 媒体服务器添加请求体
 *
 * @author takaibun
 * @since 2024/02/23
 */
@Data
public class ServerAddDto {
    private String name;
    private String host;
    private String token;
    private String description;
    private ServerType type;
    private ServerSubType subType;
}
