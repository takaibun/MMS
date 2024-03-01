package com.takaibun.plexmetadatamanager.service.impl;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.takaibun.plexmetadatamanager.entity.ServerEntity;
import com.takaibun.plexmetadatamanager.exception.ServerNotFoundException;
import com.takaibun.plexmetadatamanager.http.req.ServerAddDto;
import com.takaibun.plexmetadatamanager.http.req.ServerSearchDto;
import com.takaibun.plexmetadatamanager.http.req.ServerUpdateDto;
import com.takaibun.plexmetadatamanager.http.resp.ServerDetailsResp;
import com.takaibun.plexmetadatamanager.http.resp.ServerHealthStatusResp;
import com.takaibun.plexmetadatamanager.mapper.ServerMapper;
import com.takaibun.plexmetadatamanager.service.ServerService;

/**
 * @author takaibun
 */
@Service
public class ServerServiceImpl implements ServerService {
    private final ServerMapper serverMapper;

    public ServerServiceImpl(ServerMapper serverMapper) {
        this.serverMapper = serverMapper;
    }

    @Override
    public void add(ServerAddDto serverAddDto) {
        ServerEntity serverEntity = new ServerEntity();
        BeanUtils.copyProperties(serverAddDto, serverEntity);
        serverEntity.setId(UUID.randomUUID().toString());
        serverMapper.insert(serverEntity);
    }

    @Override
    public ServerHealthStatusResp health(String id) {
        ServerEntity serverEntity = serverMapper.selectById(id);
        if (serverEntity == null) {
            throw new ServerNotFoundException();
        }
        return new ServerHealthStatusResp();
    }

    @Override
    public void update(ServerUpdateDto serverUpdateDto) {
        ServerEntity serverEntity = serverMapper.selectById(serverUpdateDto.getId());
        if (serverEntity == null) {
            throw new ServerNotFoundException();
        }
        BeanUtils.copyProperties(serverUpdateDto, serverEntity);
        serverMapper.updateById(serverEntity);
    }

    @Override
    public void delete(String id) {
        serverMapper.deleteById(id);
    }

    @Override
    public PageInfo<ServerDetailsResp> search(ServerSearchDto serverSearchDto) {
        try (Page<ServerDetailsResp> result =
            PageHelper.startPage(serverSearchDto.getPageNum(), serverSearchDto.getPageSize())) {
            QueryWrapper<ServerEntity> queryWrapper = buildSearchQuaryWrapper(serverSearchDto);
            serverMapper.selectList(queryWrapper, resultContext -> {
                ServerDetailsResp serverDetailsResp = new ServerDetailsResp();
                BeanUtils.copyProperties(resultContext.getResultObject(), serverDetailsResp);
                result.add(serverDetailsResp);
            });
            return new PageInfo<>(result);
        }

    }

    @Override
    public ServerDetailsResp get(String id) {
        ServerEntity serverEntity = serverMapper.selectById(id);
        if (serverEntity == null) {
            throw new ServerNotFoundException();
        }
        ServerDetailsResp serverDetailsResp = new ServerDetailsResp();
        BeanUtils.copyProperties(serverEntity, serverDetailsResp);
        return serverDetailsResp;
    }

    private QueryWrapper<ServerEntity> buildSearchQuaryWrapper(ServerSearchDto serverSearchDto) {
        QueryWrapper<ServerEntity> queryWrapper = new QueryWrapper<>();
        if (serverSearchDto.getId() != null) {
            queryWrapper.eq("id", serverSearchDto.getId());
        }
        if (serverSearchDto.getName() != null) {
            queryWrapper.like("name", serverSearchDto.getName());
        }
        if (serverSearchDto.getHost() != null) {
            queryWrapper.like("host", serverSearchDto.getHost());
        }
        return queryWrapper;
    }
}
