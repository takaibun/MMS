package com.takaibun.plexmetadatamanager.exception;

/**
 * 用户未登录异常
 *
 * @author takaibun
 * @since 2024/02/26
 */
public class UserNotLoginException extends RuntimeException {
    public UserNotLoginException()
    {
        super("user is not logged in");
    }
}
