package org.school.exception;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

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

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<FormatedMessageError> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        FormatedMessageError formatedMessageError = new FormatedMessageError();
        formatedMessageError.setMessage("La taille de l'image dépasse la taille maximale autorisée");
        formatedMessageError.setCode(413);
        formatedMessageError.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(formatedMessageError);
    }

    @ExceptionHandler(FileTypeNotAllowedException.class)
    public ResponseEntity<FormatedMessageError> handleTypeNotAllowedException(FileTypeNotAllowedException ex) {
        FormatedMessageError formatedMessageError = new FormatedMessageError();
        formatedMessageError.setMessage(ex.getMessage());
        formatedMessageError.setCode(415);
        formatedMessageError.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(formatedMessageError);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<FormatedMessageError> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        FormatedMessageError formatedMessageError = new FormatedMessageError();
        formatedMessageError.setMessage("Argument invalide");
        formatedMessageError.setCode(400);
        formatedMessageError.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(formatedMessageError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<FormatedMessageError> handleException(Exception ex) {
        FormatedMessageError formatedMessageError = new FormatedMessageError();
        formatedMessageError.setMessage(ex.getMessage());
        formatedMessageError.setCode(500);
        formatedMessageError.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(formatedMessageError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<FormatedMessageError> handleException(DataIntegrityViolationException ex) {
        FormatedMessageError formatedMessageError = new FormatedMessageError();
        formatedMessageError.setMessage("Violation de contrainte d'unicité");
        formatedMessageError.setCode(500);
        formatedMessageError.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(formatedMessageError);
    }

}
