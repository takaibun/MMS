package com.takaibun.plexmetadatamanager.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.takaibun.plexmetadatamanager.enums.ServerStatus;
import com.takaibun.plexmetadatamanager.enums.ServerSubType;
import com.takaibun.plexmetadatamanager.enums.ServerType;
import com.takaibun.plexmetadatamanager.handler.EnumTypeHandler;

import lombok.Data;

/**
 * 媒体服务器实体
 *
 * @author takaibun
 * @since 2024/02/24
 */
@Data
@TableName(value = "t_msm_server", autoResultMap = true)
public class ServerEntity {
    private String id;

    private String name;

    private String host;

    private String token;

    private String description;

    @TableField(typeHandler = EnumTypeHandler.class)
    private ServerType type;

    @TableField(typeHandler = EnumTypeHandler.class)
    private ServerSubType subType;

    @TableField(typeHandler = EnumTypeHandler.class)
    private ServerStatus status;

    private Date createdAt;

    private Date updatedAt;
}
