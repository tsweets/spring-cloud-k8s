package org.beer30.springcloud.cardholder.controller;

import org.beer30.springcloud.cardholder.exception.CardholderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CardholderControllerAdvice {

    @ExceptionHandler(CardholderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cardholderNotFoundHandler(CardholderNotFoundException ex) {
        return ex.getMessage();
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errorsMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> { String fieldName = ((FieldError) error).getField();

        String errorMessage = error.getDefaultMessage();
        errorsMap.put(fieldName, errorMessage); });
        return errorsMap;
    }


}
