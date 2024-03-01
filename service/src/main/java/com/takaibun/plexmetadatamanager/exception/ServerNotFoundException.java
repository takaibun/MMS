package com.takaibun.plexmetadatamanager.exception;

/**
 * 媒体服务器不存在
 *
 * @author takaibun
 * @since 2024/02/24
 */
public class ServerNotFoundException extends RuntimeException {
    public ServerNotFoundException() {
        super("media server not found");
    }
}
