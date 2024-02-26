package com.takaibun.plexmetadatamanager.http.req;

import lombok.Data;

/**
 * 媒体服务器更新
 *
 * @author takaibun
 * @since 2024/02/24
 */
@Data
public class MediaServerUpdateDto {
    private String mediaServerId;

    private String mediaServerName;

    private String mediaServerHost;

    private String mediaServerToken;
}
