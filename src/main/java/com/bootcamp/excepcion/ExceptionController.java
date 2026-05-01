package com.bootcamp.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e){
        ApiExceptionBody apiExceptionBody =new ApiExceptionBody(e.getMessage(), ZonedDateTime.now());
        return new ResponseEntity(apiExceptionBody,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e){
        ApiExceptionBody apiExceptionBody =new ApiExceptionBody(e.getMessage() , ZonedDateTime.now());
        return new ResponseEntity(apiExceptionBody,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception e){
        ApiExceptionBody apiExceptionBody =new ApiExceptionBody(e.getMessage() , ZonedDateTime.now());
        return new ResponseEntity(apiExceptionBody,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
