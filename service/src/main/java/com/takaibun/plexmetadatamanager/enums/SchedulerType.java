package com.takaibun.plexmetadatamanager.enums;

import lombok.Getter;

/**
 * 调度器类型
 *
 * @author takaibun
 * @since 2024/03/02
 */
@Getter
public enum SchedulerType implements BaseEnum {
    /**
     * 手动触发
     */
    SIMPLE(0),
    /**
     * 定时
     */
    CRON(1),
    /**
     * 间隔触发
     */
    INTERVAL(2),
    /**
     * WebHook
     */
    WEBHOOK(3),
    /**
     * 级联触发
     */
    CALENDAR(4);

    private final int code;

    SchedulerType(int code) {
        this.code = code;
    }
}