package com.luv2code.controller;

import com.luv2code.exception.StudentNotFoundException;
import com.luv2code.model.Student;
import com.luv2code.model.StudentErrorResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudent;

    //define @Postconstruct to load student data ... only once!
    @PostConstruct
    public void loadData(){
        theStudent = new ArrayList<>();
        theStudent.add(new Student("Anaya", "Banerjee"));
        theStudent.add(new Student("Ashwani", "Singh"));
        theStudent.add(new Student("Ananya", "Banerjee"));
        theStudent.add(new Student("Anaisha", "Banerjee"));
    }

    //define endpoint for student - return list of students
    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudent;
    }

    //define endpoint to return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable("studentId") int id){
        //check the studentId against the list size
        if(id >= theStudent.size() || id<0){
            throw new StudentNotFoundException("Student id not found " + id);
        }
        //just index into the list
        return theStudent.get(id);
    }

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
