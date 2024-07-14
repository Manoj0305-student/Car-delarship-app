package com.vw.exceptions;

import com.vw.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ErrorResponse> IdNotFoundExceptionHandler(IdNotFoundException e) {
        ErrorResponse err = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ListOfCarIsEmptyException.class)
    public ResponseEntity<ErrorResponse> ListOfCarIsEmptyException(ListOfCarIsEmptyException e) {
        ErrorResponse err = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AppointmentException.class)
    public ResponseEntity<ErrorResponse> AppointmentException(AppointmentException e) {
        ErrorResponse err = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(SQLIntegrityConstraintViolationException ex) {
        String message = ex.getMessage();
        // Extract key and value from the message
        String key = message.split("'")[1];
        String customMessage = "Duplicate entry found for key: " + key;
        return ResponseEntity.status(HttpStatus.CONFLICT).body(customMessage);
    }
// 1.send the details of the car after customer hit appointment api.
// 2. change data-type of model (int->String)
// 3. email if it exists then he should get email already exists
// 4. null pointer exception.

}
