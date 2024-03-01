package com.takaibun.plexmetadatamanager.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.takaibun.plexmetadatamanager.http.req.TaskCreateDto;
import com.takaibun.plexmetadatamanager.http.req.TaskSearchDto;
import com.takaibun.plexmetadatamanager.http.req.TaskUpdateDto;
import com.takaibun.plexmetadatamanager.http.resp.TaskDetailsResp;

import java.util.List;

/**
 * @author takaibun
 */
public interface TaskService {
    /**
     * 创建任务
     *
     * @param taskCreateDto 任务创建请求
     * @return 任务创建响应
     */
    void add(TaskCreateDto taskCreateDto);

    /**
     * 删除任务
     *
     * @param id 任务ID
     */
    void delete(String id);

    /**
     * 更新任务
     *
     * @param taskUpdateDto 任务更新请求
     */
    void update(TaskUpdateDto taskUpdateDto);

    /**
     * 获取所有任务
     *
     * @return 任务列表
     */
    PageInfo<TaskDetailsResp> search(TaskSearchDto taskSearchDto);

    /**
     * 获取任务详情
     *
     * @param id 任务ID
     * @return 任务详情
     */
    TaskDetailsResp get(String id);

    /**
     * 开始任务
     *
     * @param id 任务ID
     */
    void start(String id);

    /**
     * 停止任务
     *
     * @param id 任务ID
     */
    void stop(String id);

    /**
     * 触发任务
     * @param id 任务ID
     */
    void trigger(String id);
}
