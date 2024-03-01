package com.takaibun.plexmetadatamanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.takaibun.plexmetadatamanager.entity.ServerEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 媒体服务器 Mapper 接口
 *
 * @author takaibun
 * @since 2024/02/24
 */
@Mapper
public interface ServerMapper extends BaseMapper<ServerEntity> {
}
