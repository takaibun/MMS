package com.takaibun.plexmetadatamanager.enums;

import lombok.Getter;

/**
 * 任务状态
 *
 * @author takaibun
 * @since 2024/03/01
 */
@Getter
public enum TaskStatus implements BaseEnum {
    /**
     * 初始化
     */
    INITIAL(0),
    /**
     * 启动
     */
    START(1),
    /**
     * 停止
     */
    STOP(2),
    /**
     * 异常
     */
    EXCEPTION(-1);
    private final int code;

    TaskStatus(int code) {
        this.code = code;
    }

}