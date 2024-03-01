package com.takaibun.plexmetadatamanager.quartz.exception;

/**
 * QuartzAddJobException
 *
 * @author takaibun
 * @since 2024/3/1
 */
public class QuartzSchedulerException extends RuntimeException {
    public QuartzSchedulerException(String message) {
        super(String.format("quart job add fail: %s", message));
    }
}
