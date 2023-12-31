package com.luv2code.controller;

import com.luv2code.exception.StudentNotFoundException;
import com.luv2code.model.Student;
import jakarta.annotation.PostConstruct;
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
    public Student getStudent(@PathVariable("studentId") int id) {
        //check the studentId against the list size
        if (id >= theStudent.size() || id < 0) {
            throw new StudentNotFoundException("Student id not found " + id);
        }
        //just index into the list
        return theStudent.get(id);
    }
}
