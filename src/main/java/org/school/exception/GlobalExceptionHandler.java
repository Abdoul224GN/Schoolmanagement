package org.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<FormatedMessageError> handleResourceNotFoundException(ResourceNotFoundException ex) {
        FormatedMessageError formatedMessageError = new FormatedMessageError();
        formatedMessageError.setMessage(ex.getMessage());
        formatedMessageError.setCode(404);
        formatedMessageError.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(formatedMessageError);
    }
}
