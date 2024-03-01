package com.takaibun.plexmetadatamanager.exception;

import com.takaibun.plexmetadatamanager.http.resp.ExceptionResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

/**
 * 全局异常处理
 *
 * @author takaibun
 * @since 2024/02/24
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        String errorMessage = "Invalid argument: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SQLException.class)
    public ResponseEntity<ExceptionResp> handleSQLException(SQLException ex) {
        ex.printStackTrace();
        String message = ex.getLocalizedMessage();
        ExceptionResp exceptionResp = new ExceptionResp();
        if (message == null || message.isEmpty()) {
            exceptionResp.setMessage("Internal server error");
        } else {
            exceptionResp.setMessage(message);
        }
        return new ResponseEntity<>(exceptionResp, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionResp> handleException(Exception ex) {
        ex.printStackTrace();
        String message = ex.getLocalizedMessage();
        ExceptionResp exceptionResp = new ExceptionResp();
        if (message == null || message.isEmpty()) {
            exceptionResp.setMessage("Internal server error");
        } else {
            exceptionResp.setMessage(message);
        }

        return new ResponseEntity<>(exceptionResp, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
