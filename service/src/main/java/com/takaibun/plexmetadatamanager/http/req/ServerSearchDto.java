package com.takaibun.plexmetadatamanager.http.req;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.takaibun.plexmetadatamanager.enums.ServerStatus;
import com.takaibun.plexmetadatamanager.enums.ServerSubType;
import com.takaibun.plexmetadatamanager.enums.ServerType;
import com.takaibun.plexmetadatamanager.handler.EnumTypeHandler;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 服务搜索请求体
 *
 * @author takaibun
 * @since 2024/03/02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServerSearchDto extends PageBase {
    private String id;

    private String name;

    private String host;

    private String token;

    private String description;

    private ServerType type;

    private ServerSubType subType;

    @TableField(typeHandler = EnumTypeHandler.class)
    private ServerStatus status;

    private Date createdAt;

    private Date updatedAt;
}
