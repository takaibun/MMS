package com.takaibun.plexmetadatamanager.http.resp;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * 异常响应体
 * 
 * @author takaibun
 * @since 2024/02/26
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ExceptionResp {
    private String errorCode;

    private String message;
}
