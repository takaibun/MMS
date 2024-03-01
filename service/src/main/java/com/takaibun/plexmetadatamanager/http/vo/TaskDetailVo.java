package com.takaibun.plexmetadatamanager.http.vo;

import lombok.Data;

import java.util.Map;

/**
 * 任务详情vo
 *
 * @author takaibun
 * @since 2024/03/01
 */
@Data
public class TaskDetailVo {
    private String id;

    private String name;

    private String groupId;

    private String description;

    private TaskType taskType;

    private SchedulerType schedulerType;

    private TaskStatus taskStatus;

    private Map<String, Object> taskParams;

    /**
     * 任务类型
     */
    public enum TaskType {
        /**
         * 媒体服务健康检查
         */
        MEDIA_SERVER_HEALTH("MediaServerHealth");
        private final String name;

        TaskType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 调度器类型
     */
    public enum SchedulerType {
        /**
         * 定时
         */
        CRON,
        /**
         * 间隔触发
         */
        INTERVAL,
        /**
         * 级联触发
         */
        CALENDAR,
        /**
         * 手动触发
         */
        SIMPLE,
        /**
         * WebHook
         */
        WEBHOOK
    }

    /**
     * 任务状态
     */
    public enum TaskStatus {
        /**
         * 启动
         */
        START,
        /**
         * 停止
         */
        STOP,
        /**
         * 异常
         */
        EXCEPTION
    }

}
