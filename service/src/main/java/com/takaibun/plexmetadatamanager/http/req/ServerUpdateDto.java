package com.takaibun.plexmetadatamanager.http.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.takaibun.plexmetadatamanager.enums.ServerSubType;
import com.takaibun.plexmetadatamanager.enums.ServerType;

import lombok.Data;

/**
 * 服务更新请求体
 *
 * @author takaibun
 * @since 2024/02/24
 */
@Data
public class ServerUpdateDto {
    @JsonIgnore
    private String id;

    private String name;

    private String host;

    private String token;

    private String description;

    private ServerType type;

    private ServerSubType subType;
}
