package com.takaibun.plexmetadatamanager.service;

import com.takaibun.plexmetadatamanager.http.vo.TaskDetailVo;

/**
 * 调度服务
 *
 * @author takaibun
 * @since 2024/03/01
 */
public interface SchedulerService {
    /**
     * 添加任务
     *
     * @param taskDetail 任务详情
     */
    void addTask(TaskDetailVo taskDetail);

    /**
     * 删除任务
     *
     * @param taskDetail 任务详情
     */
    void deleteTask(TaskDetailVo taskDetail);


    /**
     * 更新任务
     *
     * @param taskDetail 任务详情
     */
    void updateTask(TaskDetailVo taskDetail);


    /**
     * 启动任务
     *
     * @param taskDetail 任务详情
     */
    void startTask(TaskDetailVo taskDetail);

    /**
     * 停止任务
     *
     * @param taskDetail 任务详情
     */
    void stopTask(TaskDetailVo taskDetail);

    /**
     * 暂停任务
     *
     * @param taskDetail 任务详情
     */
    void pauseTask(TaskDetailVo taskDetail);

    /**
     * 恢复任务
     *
     * @param taskDetail 任务详情
     */
    void resumeTask(TaskDetailVo taskDetail);

    /**
     * 触发任务
     *
     * @param taskDetail 任务详情
     */
    void triggerTask(TaskDetailVo taskDetail);
}
