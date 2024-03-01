package com.takaibun.plexmetadatamanager.enums;

import lombok.Getter;

/**
 * ServerType
 *
 * @author takaibun
 * @since 2024/3/2
 */
@Getter
public enum ServerSubType implements BaseEnum {
    /**
     * 未知
     */
    UNKNOWN(0, "unknown"),
    /**
     * Plex
     */
    PLEX(1, "plex");

    private final int code;

    private final String name;

    ServerSubType(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
