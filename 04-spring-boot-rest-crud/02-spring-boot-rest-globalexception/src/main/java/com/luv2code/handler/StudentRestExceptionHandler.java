package com.luv2code.handler;

import com.luv2code.exception.StudentNotFoundException;
import com.luv2code.model.StudentErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

    //Add an exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){

        //Create a student error response
        StudentErrorResponse studentErrorResponse = new StudentErrorResponse();
        studentErrorResponse.setMessage(exc.getMessage());
        studentErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        studentErrorResponse.setTimestamp(System.currentTimeMillis());

        //return responseentity
        return new ResponseEntity<>(studentErrorResponse, HttpStatus.NOT_FOUND);
    }

    //Add another exception handler to catch any exception
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
        //Create a student error response
        StudentErrorResponse studentErrorResponse = new StudentErrorResponse();
        studentErrorResponse.setMessage(exc.getMessage());
        studentErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        studentErrorResponse.setTimestamp(System.currentTimeMillis());

        //return responseentity
        return new ResponseEntity<>(studentErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
