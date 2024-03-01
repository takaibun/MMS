package com.takaibun.plexmetadatamanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.takaibun.plexmetadatamanager.http.req.ServerAddDto;
import com.takaibun.plexmetadatamanager.http.req.ServerSearchDto;
import com.takaibun.plexmetadatamanager.http.req.ServerUpdateDto;
import com.takaibun.plexmetadatamanager.http.resp.ServerDetailsResp;
import com.takaibun.plexmetadatamanager.http.resp.ServerHealthStatusResp;
import com.takaibun.plexmetadatamanager.service.ServerService;

/**
 * 媒体服务器管理
 *
 * @author takaibun
 * @since 2024/02/23
 */
@RestController
@RequestMapping(value = "/server")
public class ServerController {

    private final ServerService serverService;

    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }

    /**
     * 获取所有服务器
     *
     * @return 所有服务器
     */
    @GetMapping
    public ResponseEntity<PageInfo<ServerDetailsResp>> search(ServerSearchDto serverSearchDto) {
        return ResponseEntity.ok(serverService.search(serverSearchDto));
    }

    /**
     * 获取服务器详情
     *
     * @param id 服务器ID
     * @return 服务器详情
     */
    @GetMapping("/{id}")
    public ResponseEntity<ServerDetailsResp> get(@PathVariable("id") String id) {
        return ResponseEntity.ok(serverService.get(id));
    }

    /**
     * 添加服务器
     *
     * @param serverAddDto 媒体服务器添加DTO
     * @return 添加响应
     */
    @PostMapping
    public ResponseEntity<Void> add(@RequestBody ServerAddDto serverAddDto) {
        serverService.add(serverAddDto);
        return ResponseEntity.ok().build();
    }

    /**
     * 更新服务器
     *
     * @param id 服务器ID
     * @param serverUpdateDto 媒体服务器更新DTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody ServerUpdateDto serverUpdateDto) {
        serverUpdateDto.setId(id);
        serverService.update(serverUpdateDto);
        return ResponseEntity.ok().build();
    }

    /**
     * 删除服务器
     *
     * @param id 服务器ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        serverService.delete(id);
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
        return ResponseEntity.ok(serverService.health(id));
    }
}
