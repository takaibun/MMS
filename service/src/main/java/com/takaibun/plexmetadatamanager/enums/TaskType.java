package com.takaibun.plexmetadatamanager.enums;

import lombok.Getter;

/**
 * 任务类型
 *
 * @author takaibun
 * @since 2024/03/02
 */
@Getter
public enum TaskType implements BaseEnum {
    /**
     * 媒体服务健康检查
     */
    MEDIA_SERVER_HEALTH(0, "MediaServerHealth");
    private final int code;
    private final String name;

    TaskType(int code, String name) {
        this.code = code;
        this.name = name;
    }
}