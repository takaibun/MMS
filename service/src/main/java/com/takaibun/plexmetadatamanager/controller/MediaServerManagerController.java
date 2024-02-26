package com.takaibun.plexmetadatamanager.controller;

import com.takaibun.plexmetadatamanager.http.req.MediaServerAddDto;
import com.takaibun.plexmetadatamanager.http.req.MediaServerSearchDto;
import com.takaibun.plexmetadatamanager.http.req.MediaServerUpdateDto;
import com.takaibun.plexmetadatamanager.http.resp.MediaServerDetailsResp;
import com.takaibun.plexmetadatamanager.http.resp.MediaServerHealthStatusResp;
import com.takaibun.plexmetadatamanager.http.resp.MediaServerUpdateResp;
import com.takaibun.plexmetadatamanager.service.MediaServerManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 媒体服务器管理
 *
 * @author takaibun
 * @since 2024/02/23
 */
@RestController
@RequestMapping(value = "/server")
public class MediaServerManagerController {

    private final MediaServerManagerService mediaServerManagerService;

    public MediaServerManagerController(MediaServerManagerService mediaServerManagerService) {
        this.mediaServerManagerService = mediaServerManagerService;
    }

    /**
     * 获取所有服务器
     *
     * @return 所有服务器
     */
    @GetMapping
    public ResponseEntity<List<MediaServerDetailsResp>> search(MediaServerSearchDto mediaServerSearchDto) {
        return ResponseEntity.ok(mediaServerManagerService.search(mediaServerSearchDto));
    }

    /**
     * 获取服务器详情
     *
     * @param id 服务器ID
     * @return 服务器详情
     */
    @GetMapping("/{id}")
    public ResponseEntity<MediaServerDetailsResp> get(@PathVariable("id") String id) {
        return ResponseEntity.ok(mediaServerManagerService.get(id));
    }

    /**
     * 添加服务器
     *
     * @param mediaServerAddDto 媒体服务器添加DTO
     * @return 添加响应
     */
    @PostMapping
    public ResponseEntity<Void> add(MediaServerAddDto mediaServerAddDto) {
        mediaServerManagerService.add(mediaServerAddDto);
        return ResponseEntity.ok().build();
    }

    /**
     * 更新服务器
     *
     * @param id                   服务器ID
     * @param mediaServerUpdateDto 媒体服务器更新DTO
     * @return 更新响应
     */
    @PutMapping("/{id}")
    public ResponseEntity<MediaServerUpdateResp> update(@PathVariable("id") String id, @RequestBody MediaServerUpdateDto mediaServerUpdateDto) {
        mediaServerUpdateDto.setMediaServerId(id);
        mediaServerManagerService.update(mediaServerUpdateDto);
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
        mediaServerManagerService.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * 检查服务器健康状态
     *
     * @param id 服务器ID
     * @return 健康状态响应
     */
    @GetMapping("/{id}/health")
    public ResponseEntity<MediaServerHealthStatusResp> health(@PathVariable("id") String id) {
        return ResponseEntity.ok(mediaServerManagerService.health(id));
    }
}
