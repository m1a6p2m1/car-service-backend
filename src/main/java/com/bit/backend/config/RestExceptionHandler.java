package com.bit.backend.config;

import com.bit.backend.dtos.ErrorDto;
import com.bit.backend.exceptions.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {AppException.class })
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(AppException appException) {
        return ResponseEntity.status(appException.getHttpStatus()).body(new ErrorDto(appException.getMessage()));
    }
}
