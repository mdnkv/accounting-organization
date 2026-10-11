package dev.mednikov.accounting.organization.controllers;

import dev.mednikov.accounting.organization.exceptions.OrganizationDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class OrganizationRestControllerAdvice {

    @ExceptionHandler(OrganizationDoesNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String organizationDoesNotExistHandler (OrganizationDoesNotExistException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> methodArgumentNotValidHandler (MethodArgumentNotValidException ex){
        Map<String, String> map = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            String fieldName = ((FieldError) objectError).getField();
            String message = objectError.getDefaultMessage();
            map.put(fieldName, message);
        });
        return map;
    }

}
