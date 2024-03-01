package com.takaibun.plexmetadatamanager.http.resp;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.takaibun.plexmetadatamanager.enums.ServerStatus;
import com.takaibun.plexmetadatamanager.enums.ServerSubType;
import com.takaibun.plexmetadatamanager.enums.ServerType;
import com.takaibun.plexmetadatamanager.handler.EnumDateSerializer;

import lombok.Data;

/**
 * 搜索服务响应体
 *
 * @author takaibun
 * @since 2024/03/02
 */
@Data
public class ServerDetailsResp {
    private String id;

    private String name;

    private String host;

    private String token;

    private String description;

    @JsonSerialize(using = EnumDateSerializer.class)
    private ServerType type;

    @JsonSerialize(using = EnumDateSerializer.class)
    private ServerSubType subType;

    @JsonSerialize(using = EnumDateSerializer.class)
    private ServerStatus status;

    private Date createdAt;

    private Date updatedAt;
}
