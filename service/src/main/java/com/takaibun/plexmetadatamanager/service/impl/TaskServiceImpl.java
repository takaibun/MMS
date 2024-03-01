package com.takaibun.plexmetadatamanager.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.takaibun.plexmetadatamanager.entity.TaskEntity;
import com.takaibun.plexmetadatamanager.enums.TaskStatus;
import com.takaibun.plexmetadatamanager.exception.TaskNotFoundException;
import com.takaibun.plexmetadatamanager.http.req.TaskCreateDto;
import com.takaibun.plexmetadatamanager.http.req.TaskSearchDto;
import com.takaibun.plexmetadatamanager.http.req.TaskUpdateDto;
import com.takaibun.plexmetadatamanager.http.resp.TaskDetailsResp;
import com.takaibun.plexmetadatamanager.http.vo.TaskDetailVo;
import com.takaibun.plexmetadatamanager.mapper.TaskMapper;
import com.takaibun.plexmetadatamanager.service.SchedulerService;
import com.takaibun.plexmetadatamanager.service.TaskService;

/**
 * 任务管理器服务实现类
 *
 * @author takaibun
 * @since 2024/02/24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TaskServiceImpl implements TaskService {
    private final TaskMapper taskMapper;

    private final SchedulerService schedulerService;

    public TaskServiceImpl(TaskMapper taskMapper, SchedulerService schedulerService) {
        this.taskMapper = taskMapper;
        this.schedulerService = schedulerService;
    }

    @Override
    public void add(TaskCreateDto taskCreateDto) {
        TaskEntity taskEntity = new TaskEntity();
        BeanUtils.copyProperties(taskCreateDto, taskEntity);
        taskEntity.setId(UUID.randomUUID().toString());
        taskEntity.setTaskStatus(TaskStatus.INITIAL);
        taskEntity.setCreatedAt(new Date());
        taskEntity.setUpdatedAt(new Date());
        taskMapper.insert(taskEntity);
        schedulerService.addTask(getTaskDetailVo(taskEntity));
    }

    @Override
    public void delete(String id) {
        TaskEntity taskEntity = taskMapper.selectById(id);
        if (taskEntity == null) {
            throw new TaskNotFoundException();
        }
        taskMapper.deleteById(id);
        schedulerService.deleteTask(getTaskDetailVo(taskEntity));
    }

    @Override
    public void update(TaskUpdateDto taskUpdateDto) {
        TaskEntity taskEntity = taskMapper.selectById(taskUpdateDto.getId());
        if (taskEntity == null) {
            throw new TaskNotFoundException();
        }
        BeanUtils.copyProperties(taskUpdateDto, taskEntity);
        taskEntity.setUpdatedAt(new Date());
        taskMapper.updateById(taskEntity);
        schedulerService.updateTask(getTaskDetailVo(taskEntity));
    }

    @Override
    public PageInfo<TaskDetailsResp> search(TaskSearchDto taskSearchDto) {
        try (Page<TaskDetailsResp> results =
            PageHelper.startPage(taskSearchDto.getPageNum(), taskSearchDto.getPageSize())) {
            QueryWrapper<TaskEntity> queryWrapper = new QueryWrapper<>();
            if (taskSearchDto.getTaskId() != null) {
                queryWrapper.eq("id", taskSearchDto.getTaskId());
            }
            taskMapper.selectList(queryWrapper, resultContext -> {
                TaskDetailsResp taskDetailsResp = new TaskDetailsResp();
                BeanUtils.copyProperties(resultContext.getResultObject(), taskDetailsResp);
                results.add(taskDetailsResp);
            });
            return new PageInfo<>(results);
        }

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
        TaskEntity taskEntity = taskMapper.selectById(id);
        taskEntity.setTaskStatus(TaskStatus.START);
        taskMapper.updateById(taskEntity);
        schedulerService.startTask(getTaskDetailVo(taskEntity));
    }

    @Override
    public void stop(String id) {
        TaskEntity taskEntity = taskMapper.selectById(id);
        taskEntity.setTaskStatus(TaskStatus.STOP);
        taskMapper.updateById(taskEntity);
        schedulerService.stopTask(getTaskDetailVo(taskEntity));
    }

    @Override
    public void trigger(String id) {
        TaskEntity taskEntity = taskMapper.selectById(id);
        schedulerService.triggerTask(getTaskDetailVo(taskEntity));
    }

    private TaskDetailVo getTaskDetailVo(TaskEntity taskEntity) {
        TaskDetailVo taskDetail = new TaskDetailVo();
        BeanUtils.copyProperties(taskEntity, taskDetail);
        return taskDetail;
    }
}
