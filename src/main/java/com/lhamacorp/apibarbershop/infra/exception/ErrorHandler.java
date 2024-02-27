package com.lhamacorp.apibarbershop.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404(){

        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException exception){

        var errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(BeanValidationErrorDTO::new).toList());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity handleBusinessRuleException(ValidationException exception){

        String exMsg = exception.getMessage();
        return ResponseEntity.badRequest().body(new ScheduleValidationErrorDTO(exMsg));
    }

    private record BeanValidationErrorDTO(String field, String message){

        public BeanValidationErrorDTO(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

    private record ScheduleValidationErrorDTO(String message){

    }

}
