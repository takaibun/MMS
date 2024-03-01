package com.takaibun.plexmetadatamanager.http.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServerSearchDto extends PageBase {
    private String mediaServerId;
    private String mediaServerName;
    private String mediaServerHost;
}
