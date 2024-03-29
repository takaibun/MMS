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
    SERVER_HEALTH_CHECK(0, "ServerHealthCheck");

    private final int code;
    private final String name;

    TaskType(int code, String name) {
        this.code = code;
        this.name = name;
    }
}