package com.takaibun.plexmetadatamanager.http.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ExceptionResp {
    private String errorCode;

    private String message;
}
