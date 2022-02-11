package com.example.studentmanagementservice.exception;

import com.example.studentmanagementservice.exception.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(DataNotFoundException e){
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        final ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                badRequest,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, badRequest);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleOtherException(DataNotFoundException e){
        final HttpStatus badRequest = HttpStatus.INTERNAL_SERVER_ERROR;
        final ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                badRequest,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, badRequest);
    }
}
