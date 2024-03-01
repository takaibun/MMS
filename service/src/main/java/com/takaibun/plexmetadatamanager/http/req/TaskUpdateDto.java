package com.takaibun.plexmetadatamanager.http.req;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.takaibun.plexmetadatamanager.enums.SchedulerType;
import com.takaibun.plexmetadatamanager.enums.TaskType;

import lombok.Data;
/**
 * 任务更新请求体
 *
 * @author takaibun
 * @since 2024/03/02
 */
@Data
public class TaskUpdateDto {
    @JsonIgnore
    private String id;

    private String name;

    private String description;

    private TaskType taskType;

    private SchedulerType schedulerType;

    private Map<String, Object> taskParams;
}
