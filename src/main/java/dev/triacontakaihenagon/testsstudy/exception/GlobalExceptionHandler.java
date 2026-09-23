package dev.triacontakaihenagon.testsstudy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            TaskNotFoundException.class, UserNotFoundException.class,
            CategoryNotFoundException.class, LabelNotFoundException.class
    }) public ResponseEntity<String> notFound(RuntimeException err) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
    }
}