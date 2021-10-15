package com.example.demo.expections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class AppExceptionHandler {
  @ExceptionHandler(value = {ApplicationException.class})
  public ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException e) {
    ErrorBody error = new ErrorBody(e.getCode(), e.getMessage(), e.getInfo());
    ErrorResponse response = new ErrorResponse(error);
    HttpStatus statuCode =
        e.getStatusCode() == null ? HttpStatus.INTERNAL_SERVER_ERROR : e.getStatusCode();
    return new ResponseEntity<>(response, statuCode);
  }

  @ExceptionHandler(value = {MethodArgumentNotValidException.class})
  public ResponseEntity<ErrorResponse> handleConstraintViolationException(
      MethodArgumentNotValidException e) {
    List<ErrorBody> errors = new ArrayList<>();
    e.getBindingResult()
        .getAllErrors()
        .forEach(
            er -> {
              String fieldName = ((FieldError) er).getField();
              String errorMessage = er.getDefaultMessage();
              errors.add(new ErrorBody(null, errorMessage, fieldName));
            });
    ErrorResponse response = new ErrorResponse(errors);
    HttpStatus statusCode = HttpStatus.BAD_REQUEST;
    return new ResponseEntity<>(response, statusCode);
  }

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<ErrorResponse> handleRandomException(Exception e) {
    ErrorBody error = new ErrorBody(null, e.getMessage(), null);
    ErrorResponse response = new ErrorResponse(error);
    HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    return new ResponseEntity<>(response, statusCode);
  }
}