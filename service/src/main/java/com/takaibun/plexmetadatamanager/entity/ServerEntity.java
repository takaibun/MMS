package com.takaibun.plexmetadatamanager.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 媒体服务器实体
 *
 * @author takaibun
 * @since 2024/02/24
 */
@Data
@TableName("t_msm_server_detail")
public class ServerEntity {
    private String id;

    private String name;

    private String host;

    private String token;
}
