package com.takaibun.plexmetadatamanager.http.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.takaibun.plexmetadatamanager.enums.SchedulerType;
import com.takaibun.plexmetadatamanager.enums.TaskType;
import lombok.Data;

import java.util.Map;

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
