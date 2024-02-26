package com.takaibun.plexmetadatamanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.takaibun.plexmetadatamanager.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper
 *
 * @author takaibun
 * @since 2024/02/24
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
