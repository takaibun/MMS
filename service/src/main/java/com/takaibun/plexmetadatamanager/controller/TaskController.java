package com.takaibun.plexmetadatamanager.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.takaibun.plexmetadatamanager.http.req.TaskCreateDto;
import com.takaibun.plexmetadatamanager.http.req.TaskSearchDto;
import com.takaibun.plexmetadatamanager.http.req.TaskUpdateDto;
import com.takaibun.plexmetadatamanager.http.resp.TaskDetailsResp;
import com.takaibun.plexmetadatamanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 任务管理
 *
 * @author takaibun
 * @since 2024/02/23
 */
@RestController
@RequestMapping(value = "/task")
public class TaskController {
    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * 获取所有任务
     *
     * @return 所有任务响应
     */
    @GetMapping
    public ResponseEntity<PageInfo<TaskDetailsResp>> search(@RequestBody TaskSearchDto taskSearchDto) {
        return ResponseEntity.ok(taskService.search(taskSearchDto));
    }

    /**
     * 获取任务详情
     *
     * @param id 任务id
     * @return 任务详情响应
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskDetailsResp> get(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(taskService.get(id));
    }

    /**
     * 添加任务
     *
     * @param taskCreateDto 任务创建Dto
     * @return 任务创建响应
     */
    @PostMapping
    public ResponseEntity<Void> add(@RequestBody TaskCreateDto taskCreateDto) {
        taskService.add(taskCreateDto);
        return ResponseEntity.ok().build();
    }


    /**
     * 删除任务
     *
     * @param id 任务id
     * @return 删除响应
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") String id) {
        taskService.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * 更新任务
     *
     * @param id            任务id
     * @param taskUpdateDto 任务更新Dto
     * @return 更新响应
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(value = "id") String id, @RequestBody TaskUpdateDto taskUpdateDto) {
        taskUpdateDto.setId(id);
        taskService.update(taskUpdateDto);
        return ResponseEntity.ok().build();
    }

    /**
     * 启动任务
     *
     * @param id 任务id
     * @return 启动响应
     */
    @PostMapping("/{id}/start")
    public ResponseEntity<Void> start(@PathVariable(value = "id") String id) {
        taskService.start(id);
        return ResponseEntity.ok().build();
    }

    /**
     * 停止任务
     *
     * @param id 任务id
     * @return 停止响应
     */
    @PostMapping("/{id}/stop")
    public ResponseEntity<Void> stop(@PathVariable(value = "id") String id) {
        taskService.stop(id);
        return ResponseEntity.ok().build();
    }

    /**
     * 执行任务
     *
     * @param id 任务id
     * @return 执行响应
     */
    @PostMapping("/{id}/execute")
    public ResponseEntity<Void> execute(@PathVariable(value = "id") String id) {
        taskService.trigger(id);
        return ResponseEntity.ok().build();
    }
}
