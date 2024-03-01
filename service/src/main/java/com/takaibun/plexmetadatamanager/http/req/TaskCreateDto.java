package com.takaibun.plexmetadatamanager.http.req;

import com.takaibun.plexmetadatamanager.enums.SchedulerType;
import com.takaibun.plexmetadatamanager.enums.TaskStatus;
import com.takaibun.plexmetadatamanager.enums.TaskType;
import lombok.Data;

import java.util.Map;

/**
 * 任务创建DTO
 *
 * @author takaibun
 * @since 2024/03/02
 */
@Data
public class TaskCreateDto {

    private String name;

    private String description;

    private TaskType taskType;

    private SchedulerType schedulerType;

    private Map<String, Object> taskParams;
}
