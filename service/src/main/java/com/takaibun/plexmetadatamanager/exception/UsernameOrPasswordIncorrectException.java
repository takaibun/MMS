package com.takaibun.plexmetadatamanager.exception;

/**
 * 用户名或密码错误异常
 *
 * @author takaibun
 * @since 2024/02/24
 */
public class UsernameOrPasswordIncorrectException extends RuntimeException {
    public UsernameOrPasswordIncorrectException() {
        super("username or password incorrect");
    }
}
