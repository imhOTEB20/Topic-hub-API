package com.aluralatam.topichubapirest.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorManager {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity manageError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity manageError400(MethodArgumentNotValidException e) {
        var errors = e.getFieldErrors().stream()
                .map(DTOValidationError::new)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);
    }

    private record DTOValidationError(String field, String error) {
        public DTOValidationError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
