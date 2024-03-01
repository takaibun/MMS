package com.takaibun.plexmetadatamanager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.takaibun.plexmetadatamanager.enums.SchedulerType;
import com.takaibun.plexmetadatamanager.enums.TaskStatus;
import com.takaibun.plexmetadatamanager.enums.TaskType;
import com.takaibun.plexmetadatamanager.handler.EnumTypeHandler;
import com.takaibun.plexmetadatamanager.handler.MapToJsonTypeHandler;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * 任务实体
 *
 * @author takaibun
 * @since 2024/02/24
 */
@Data
@TableName(value = "t_msm_task", autoResultMap = true)
public class TaskEntity {
    private String id;

    private String name;

    private String description;

    @TableField(typeHandler = EnumTypeHandler.class)
    private TaskType taskType;

    @TableField(typeHandler = EnumTypeHandler.class)
    private SchedulerType schedulerType;

    @TableField(typeHandler = EnumTypeHandler.class)
    private TaskStatus taskStatus;

    @TableField(typeHandler = MapToJsonTypeHandler.class)
    private Map<String, Object> taskParams;

    private Date createTime;

    private Date updateTime;
}
