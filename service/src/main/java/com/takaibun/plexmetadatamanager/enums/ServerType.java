package com.takaibun.plexmetadatamanager.enums;

import lombok.Getter;

/**
 * ServerType
 *
 * @author takaibun
 * @since 2024/3/2
 */
@Getter
public enum ServerType implements BaseEnum {
    /**
     * 未知
     */
    UNKNOWN(0, "unknown"),
    /**
     * 媒体服务器
     */
    MEDIA(1, "media");

    private final int code;

    private final String name;

    ServerType(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
