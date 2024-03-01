package com.takaibun.plexmetadatamanager.http.resp;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.takaibun.plexmetadatamanager.enums.SchedulerType;
import com.takaibun.plexmetadatamanager.enums.TaskStatus;
import com.takaibun.plexmetadatamanager.enums.TaskType;
import com.takaibun.plexmetadatamanager.handler.EnumDateSerializer;

import lombok.Data;

/**
 * 任务详情响应体
 *
 * @author takaibun
 * @since 2024/03/02
 */
@Data
public class TaskDetailsResp {
    private String id;

    private String name;

    private String description;

    @JsonSerialize(using = EnumDateSerializer.class)
    private TaskType taskType;

    @JsonSerialize(using = EnumDateSerializer.class)
    private SchedulerType schedulerType;

    @JsonSerialize(using = EnumDateSerializer.class)
    private TaskStatus taskStatus;

    private Map<String, Object> taskParams;

    private Date createdAt;

    private Date updatedAt;
}
