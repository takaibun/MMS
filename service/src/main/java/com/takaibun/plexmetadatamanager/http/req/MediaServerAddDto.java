package com.takaibun.plexmetadatamanager.http.req;

import lombok.Data;

/**
 * 媒体服务器添加请求体
 *
 * @author takaibun
 * @since 2024/02/23
 */
@Data
public class MediaServerAddDto {
    private String mediaServerName;
    private String mediaServerHost;
    private String mediaServerToken;
}
