package com.takaibun.plexmetadatamanager.enums;

/**
 * 服务状态枚举
 *
 * @author takaibun
 * @since 2024/3/2
 */
public enum ServerStatus implements BaseEnum {
    /**
     * 未知
     */
    UNKNOWN(0),
    /**
     * 正常
     */
    NORMAL(1),
    /**
     * 异常
     */
    ERROR(2);

    private final int code;

    ServerStatus(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }
}
