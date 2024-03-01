package com.takaibun.plexmetadatamanager.http.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 任务搜索请求体
 *
 * @author takaibun
 * @since 2024/02/24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TaskSearchDto extends PageBase {
    private String taskId;
}
