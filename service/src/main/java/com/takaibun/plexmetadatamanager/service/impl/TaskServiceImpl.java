package com.takaibun.plexmetadatamanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.takaibun.plexmetadatamanager.entity.TaskEntity;
import com.takaibun.plexmetadatamanager.exception.TaskNotFoundException;
import com.takaibun.plexmetadatamanager.http.req.TaskCreateDto;
import com.takaibun.plexmetadatamanager.http.req.TaskSearchDto;
import com.takaibun.plexmetadatamanager.http.req.TaskUpdateDto;
import com.takaibun.plexmetadatamanager.http.resp.TaskDetailsResp;
import com.takaibun.plexmetadatamanager.mapper.TaskMapper;
import com.takaibun.plexmetadatamanager.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 任务管理器服务实现类
 *
 * @author takaibun
 * @since 2024/02/24
 */
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public void add(TaskCreateDto taskCreateDto) {
        TaskEntity taskEntity = new TaskEntity();
        BeanUtils.copyProperties(taskCreateDto, taskEntity);
        taskEntity.setId(UUID.randomUUID().toString());
        taskMapper.insert(taskEntity);
    }

    @Override
    public void delete(String id) {
        TaskEntity taskEntity = taskMapper.selectById(id);
        if (taskEntity == null) {
            throw new TaskNotFoundException();
        }
        taskMapper.deleteById(id);
    }

    @Override
    public void update(TaskUpdateDto taskUpdateDto) {
        TaskEntity taskEntity = taskMapper.selectById(taskUpdateDto.getTaskId());
        if (taskEntity == null) {
            throw new TaskNotFoundException();
        }
        BeanUtils.copyProperties(taskUpdateDto, taskEntity);
        taskMapper.updateById(taskEntity);
    }

    @Override
    public List<TaskDetailsResp> search(TaskSearchDto taskSearchDto) {
        QueryWrapper<TaskEntity> queryWrapper = new QueryWrapper<>();
        if (taskSearchDto.getTaskId() != null) {
            queryWrapper.eq("id", taskSearchDto.getTaskId());
        }
        return taskMapper.selectObjs(queryWrapper);
    }

    @Override
    public TaskDetailsResp get(String id) {
        TaskEntity taskEntity = taskMapper.selectById(id);
        if (taskEntity == null) {
            throw new TaskNotFoundException();
        }
        TaskDetailsResp taskDetailsResp = new TaskDetailsResp();
        BeanUtils.copyProperties(taskEntity, taskDetailsResp);
        return taskDetailsResp;
    }

    @Override
    public void start(String id) {

    }

    @Override
    public void stop(String id) {

    }
}
