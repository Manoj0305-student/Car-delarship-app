package com.vw.exceptions;

import com.vw.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ErrorResponse> IdNotFoundExceptionHandler(IdNotFoundException e){
        ErrorResponse err = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ListOfCarIsEmptyException.class)
    public ResponseEntity<ErrorResponse> ListOfCarIsEmptyException(ListOfCarIsEmptyException e){
        ErrorResponse err = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AppointmentException.class)
    public ResponseEntity<ErrorResponse> AppointmentException(AppointmentException e){
        ErrorResponse err = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

}
