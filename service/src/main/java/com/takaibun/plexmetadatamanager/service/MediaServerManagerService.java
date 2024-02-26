package com.takaibun.plexmetadatamanager.service;

import com.takaibun.plexmetadatamanager.http.req.MediaServerAddDto;
import com.takaibun.plexmetadatamanager.http.req.MediaServerSearchDto;
import com.takaibun.plexmetadatamanager.http.req.MediaServerUpdateDto;
import com.takaibun.plexmetadatamanager.http.resp.MediaServerDetailsResp;
import com.takaibun.plexmetadatamanager.http.resp.MediaServerHealthStatusResp;

import java.util.List;

public interface MediaServerManagerService {
    /**
     * 添加服务器
     *
     * @param mediaServerAddDto 服务器添加信息
     */
    void add(MediaServerAddDto mediaServerAddDto);

    /**
     * 查询服务器健康状态
     *
     * @param id 服务器id
     * @return 服务器健康状态
     */
    MediaServerHealthStatusResp health(String id);

    /**
     * 更新服务器信息
     *
     * @param mediaServerUpdateDto 服务器更新信息
     */
    void update(MediaServerUpdateDto mediaServerUpdateDto);

    /**
     * 删除服务器
     *
     * @param id 服务器id
     */
    void delete(String id);

    /**
     * 查询所有服务器
     *
     * @param mediaServerSearchDto 查询条件
     * @return 所有服务器信息
     */
    List<MediaServerDetailsResp> search(MediaServerSearchDto mediaServerSearchDto);

    /**
     * 查询服务器
     *
     * @param id 服务器id
     * @return 服务器信息
     */
    MediaServerDetailsResp get(String id);
}
