package com.takaibun.plexmetadatamanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.takaibun.plexmetadatamanager.entity.MediaServerEntity;
import com.takaibun.plexmetadatamanager.exception.MediaServerNotFoundException;
import com.takaibun.plexmetadatamanager.http.req.MediaServerAddDto;
import com.takaibun.plexmetadatamanager.http.req.MediaServerSearchDto;
import com.takaibun.plexmetadatamanager.http.req.MediaServerUpdateDto;
import com.takaibun.plexmetadatamanager.http.resp.MediaServerDetailsResp;
import com.takaibun.plexmetadatamanager.http.resp.MediaServerHealthStatusResp;
import com.takaibun.plexmetadatamanager.mapper.MediaServerMapper;
import com.takaibun.plexmetadatamanager.service.MediaServerManagerService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author takaibun
 */
@Service
public class MediaServerManagerServiceImpl implements MediaServerManagerService {
    private final MediaServerMapper mediaServerMapper;

    public MediaServerManagerServiceImpl(MediaServerMapper mediaServerMapper) {
        this.mediaServerMapper = mediaServerMapper;
    }

    @Override
    public void add(MediaServerAddDto mediaServerAddDto) {
        MediaServerEntity mediaServerEntity = new MediaServerEntity();
        BeanUtils.copyProperties(mediaServerAddDto, mediaServerEntity);
        mediaServerEntity.setId(UUID.randomUUID().toString());
        mediaServerMapper.insert(mediaServerEntity);
    }

    @Override
    public MediaServerHealthStatusResp health(String id) {
        MediaServerEntity mediaServerEntity = mediaServerMapper.selectById(id);
        if (mediaServerEntity == null) {
            throw new MediaServerNotFoundException();
        }

        return new MediaServerHealthStatusResp();
    }

    @Override
    public void update(MediaServerUpdateDto mediaServerUpdateDto) {
        MediaServerEntity mediaServerEntity = mediaServerMapper.selectById(mediaServerUpdateDto.getMediaServerId());
        if (mediaServerEntity == null) {
            throw new MediaServerNotFoundException();
        }
        BeanUtils.copyProperties(mediaServerUpdateDto, mediaServerEntity);
        mediaServerMapper.updateById(mediaServerEntity);
    }

    @Override
    public void delete(String id) {
        mediaServerMapper.deleteById(id);
    }

    @Override
    public List<MediaServerDetailsResp> search(MediaServerSearchDto mediaServerSearchDto) {
        QueryWrapper<MediaServerEntity> queryWrapper = new QueryWrapper<>();
        if (mediaServerSearchDto.getMediaServerId() != null) {
            queryWrapper.eq("id", mediaServerSearchDto.getMediaServerId());
        }
        if (mediaServerSearchDto.getMediaServerName() != null) {
            queryWrapper.like("name", mediaServerSearchDto.getMediaServerName());
        }
        if (mediaServerSearchDto.getMediaServerHost() != null) {
            queryWrapper.like("host", mediaServerSearchDto.getMediaServerHost());
        }
        return mediaServerMapper.selectObjs(queryWrapper);
    }

    @Override
    public MediaServerDetailsResp get(String id) {
        MediaServerEntity mediaServerEntity = mediaServerMapper.selectById(id);
        if (mediaServerEntity == null) {
            throw new MediaServerNotFoundException();
        }
        MediaServerDetailsResp mediaServerDetailsResp = new MediaServerDetailsResp();
        BeanUtils.copyProperties(mediaServerEntity, mediaServerDetailsResp);
        return mediaServerDetailsResp;
    }
}
