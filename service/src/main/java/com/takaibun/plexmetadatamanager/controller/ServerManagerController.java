package com.takaibun.plexmetadatamanager.controller;

import com.github.pagehelper.PageInfo;
import com.takaibun.plexmetadatamanager.http.req.ServerAddDto;
import com.takaibun.plexmetadatamanager.http.req.ServerSearchDto;
import com.takaibun.plexmetadatamanager.http.req.ServerUpdateDto;
import com.takaibun.plexmetadatamanager.http.resp.ServerDetailsResp;
import com.takaibun.plexmetadatamanager.http.resp.ServerHealthStatusResp;
import com.takaibun.plexmetadatamanager.http.resp.ServerUpdateResp;
import com.takaibun.plexmetadatamanager.service.ServerManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 媒体服务器管理
 *
 * @author takaibun
 * @since 2024/02/23
 */
@RestController
@RequestMapping(value = "/server")
public class ServerManagerController {

    private final ServerManagerService serverManagerService;

    public ServerManagerController(ServerManagerService serverManagerService) {
        this.serverManagerService = serverManagerService;
    }

    /**
     * 获取所有服务器
     *
     * @return 所有服务器
     */
    @GetMapping
    public ResponseEntity<PageInfo<ServerDetailsResp>> search(ServerSearchDto serverSearchDto) {
        return ResponseEntity.ok(serverManagerService.search(serverSearchDto));
    }

    /**
     * 获取服务器详情
     *
     * @param id 服务器ID
     * @return 服务器详情
     */
    @GetMapping("/{id}")
    public ResponseEntity<ServerDetailsResp> get(@PathVariable("id") String id) {
        return ResponseEntity.ok(serverManagerService.get(id));
    }

    /**
     * 添加服务器
     *
     * @param serverAddDto 媒体服务器添加DTO
     * @return 添加响应
     */
    @PostMapping
    public ResponseEntity<Void> add(ServerAddDto serverAddDto) {
        serverManagerService.add(serverAddDto);
        return ResponseEntity.ok().build();
    }

    /**
     * 更新服务器
     *
     * @param id                   服务器ID
     * @param serverUpdateDto 媒体服务器更新DTO
     * @return 更新响应
     */
    @PutMapping("/{id}")
    public ResponseEntity<ServerUpdateResp> update(@PathVariable("id") String id, @RequestBody ServerUpdateDto serverUpdateDto) {
        serverUpdateDto.setMediaServerId(id);
        serverManagerService.update(serverUpdateDto);
        return ResponseEntity.ok().build();
    }

    /**
     * 删除服务器
     *
     * @param id 服务器ID
     * @return 删除响应
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        serverManagerService.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * 检查服务器健康状态
     *
     * @param id 服务器ID
     * @return 健康状态响应
     */
    @GetMapping("/{id}/health")
    public ResponseEntity<ServerHealthStatusResp> health(@PathVariable("id") String id) {
        return ResponseEntity.ok(serverManagerService.health(id));
    }
}
