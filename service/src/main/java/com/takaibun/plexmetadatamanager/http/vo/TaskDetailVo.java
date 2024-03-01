package com.takaibun.plexmetadatamanager.http.vo;

import java.util.Map;

import com.takaibun.plexmetadatamanager.enums.SchedulerType;
import com.takaibun.plexmetadatamanager.enums.TaskStatus;
import com.takaibun.plexmetadatamanager.enums.TaskType;

import lombok.Data;

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
}
