package com.assignment.dataapi.advice;

import com.assignment.dataapi.dto.ErrorDto;
import com.assignment.dataapi.exception.ResourceExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ResourceExistException.class })
    public ResponseEntity<ErrorDto> handleAccessDeniedException(Exception ex) {
        ErrorDto body = ErrorDto.builder().statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value()).errMsg(ex.getMessage()).build();
        return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
