package org.school.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FormatedMessageError {
    private String message;
    private Integer code;
    private LocalDateTime timestamp;
}
