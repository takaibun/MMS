package com.takaibun.plexmetadatamanager.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 任务实体
 *
 * @author takaibun
 * @since 2024/02/24
 */
@Data
@TableName("msm_task_info")
public class TaskEntity {
    private String id;
}
