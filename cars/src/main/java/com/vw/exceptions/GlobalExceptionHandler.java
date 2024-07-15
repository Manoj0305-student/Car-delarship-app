package com.vw.exceptions;

import com.vw.dto.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    public ResponseEntity<ErrorResponse> AppointmentExceptionHandler(AppointmentException e) {
        ErrorResponse err = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> DataIntegrityViolationExceptionHandler(SQLIntegrityConstraintViolationException ex) {
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

    @ExceptionHandler(ExecutiveException.class)
    public ResponseEntity<ErrorResponse> ExecutiveExceptionHandler(ExecutiveException e) {
        ErrorResponse err = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorResponse> CustomerExceptionHandler(CustomerException e) {
        ErrorResponse err = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponse> InvalidInputExceptionHandler(InvalidInputException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NoHandlerFoundException ex) {
        String message = "The requested resource could not be found. Here are some possibilities:\n" +
                "- The URL might be incorrect.\n" +
                "- You might be using the wrong HTTP method (e.g., GET instead of POST).\n" +
                "- The controller method might not be mapped with the appropriate annotation (@GetMapping, @PostMapping, etc.).";
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, message);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> ConstraintViolationExceptionHandler(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        StringBuilder messageBuilder = new StringBuilder("Validation failed for Executive entity: \n");
        for (ConstraintViolation<?> violation : constraintViolations) {
            messageBuilder.append("- ").append(violation.getPropertyPath()).append(" : ").append(violation.getMessage()).append("\n");
        }
        String message = messageBuilder.toString();
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, message);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
