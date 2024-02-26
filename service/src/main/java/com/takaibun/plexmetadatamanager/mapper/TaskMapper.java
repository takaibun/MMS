package com.takaibun.plexmetadatamanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.takaibun.plexmetadatamanager.entity.TaskEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 任务Mapper
 *
 * @author takaibun
 * @since 2024/02/24
 */
@Mapper
public interface TaskMapper extends BaseMapper<TaskEntity> {
}
