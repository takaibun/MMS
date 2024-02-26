package com.takaibun.plexmetadatamanager.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户实体
 *
 * @author takaibun
 * @since 2024/02/24
 */
@Data
@TableName("msm_user_password")
public class UserEntity {
    String username;
    String password;

}
