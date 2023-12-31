package net.learnwithfun.springbootrestapi.controller;

import net.learnwithfun.springbootrestapi.models.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    //http://localhost:8080/api/student
    @GetMapping("/student")
    public ResponseEntity<Student> getStudent() {
       // return new ResponseEntity<>(new Student(1, "Anaya", "Banerjee"), HttpStatus.OK);
    //return ResponseEntity.ok(new Student(1, "Anaya", "Banerjee"));
        return ResponseEntity.ok()
                .header("custom-header", "header1")
                .body(new Student(1, "Anaya", "Banerjee"));
    }

    //http://localhost:8080/api/students
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Ramesh", "Fadatare"));
        students.add(new Student(2, "umesh", "Fadatare"));
        students.add(new Student(3, "Ram", "Jadhav"));
        students.add(new Student(4, "Sanjay", "Pawar"));

        return ResponseEntity.ok(students);
    }

    //Spring boot rest api with path variables
    //{id} - URI Template Variable
    //http://localhost:8080/api/students/1/Anaya/Banerjee
    @GetMapping("/students/{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
        return ResponseEntity.ok(new Student(1, firstName, lastName));
    }

    // Spring boot REST API with Request Param
    //  http://localhost:8080/api/students/query?id=1&firstName=Anaya&lastName=Banerjee
    @GetMapping("students/query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        return ResponseEntity.ok(new Student(id, firstName, lastName));
    }

    // Spring boot REST API that handles HTTP POST Request - creating new resource
    // @PostMapping and @RequestBody
    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED) // Return specific Http return code
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

// Spring boot REST API that handles HTTP PUT Request - updating existing resource
    @PutMapping("/students/{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,
                                 @PathVariable("id") int studentId){
        return ResponseEntity.ok(student);
    }

    // Spring boot REST API that handles HTTP DELETE Request - deleting the existing resource
    @DeleteMapping("/students/{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id ") int studentId){
        return ResponseEntity.ok("Student deleted successfully!");
    }
}
