package com.takaibun.plexmetadatamanager.http.req;

import lombok.Data;

@Data
public class MediaServerSearchDto {
    private String mediaServerId;
    private String mediaServerName;

    private String mediaServerHost;
}
