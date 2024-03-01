package com.takaibun.plexmetadatamanager.service;

import com.github.pagehelper.PageInfo;
import com.takaibun.plexmetadatamanager.http.req.ServerAddDto;
import com.takaibun.plexmetadatamanager.http.req.ServerSearchDto;
import com.takaibun.plexmetadatamanager.http.req.ServerUpdateDto;
import com.takaibun.plexmetadatamanager.http.resp.ServerDetailsResp;
import com.takaibun.plexmetadatamanager.http.resp.ServerHealthStatusResp;

/**
 * 服务管理服务
 *
 * @author takaibun
 * @since 2024/03/02
 */
public interface ServerService {
    /**
     * 添加服务器
     *
     * @param serverAddDto 服务器添加信息
     */
    void add(ServerAddDto serverAddDto);

    /**
     * 查询服务器健康状态
     *
     * @param id 服务器id
     * @return 服务器健康状态
     */
    ServerHealthStatusResp health(String id);

    /**
     * 更新服务器信息
     *
     * @param serverUpdateDto 服务器更新信息
     */
    void update(ServerUpdateDto serverUpdateDto);

    /**
     * 删除服务器
     *
     * @param id 服务器id
     */
    void delete(String id);

    /**
     * 查询所有服务器
     *
     * @param serverSearchDto 查询条件
     * @return 所有服务器信息
     */
    PageInfo<ServerDetailsResp> search(ServerSearchDto serverSearchDto);

    /**
     * 查询服务器
     *
     * @param id 服务器id
     * @return 服务器信息
     */
    ServerDetailsResp get(String id);
}
