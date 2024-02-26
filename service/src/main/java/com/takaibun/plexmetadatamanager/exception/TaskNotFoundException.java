package com.takaibun.plexmetadatamanager.exception;

/**
 * 任务不存在异常
 *
 * @author takaibun
 * @since 2024/02/24
 */
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
        super("task not found");
    }
}
