package com.takaibun.plexmetadatamanager.http.resp;

import com.takaibun.plexmetadatamanager.enums.SchedulerType;
import com.takaibun.plexmetadatamanager.enums.TaskStatus;
import com.takaibun.plexmetadatamanager.enums.TaskType;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class TaskDetailsResp {
    private String id;

    private String name;

    private String description;

    private TaskType taskType;

    private SchedulerType schedulerType;

    private TaskStatus taskStatus;

    private Map<String, Object> taskParams;

    private Date createTime;

    private Date updateTime;
}
