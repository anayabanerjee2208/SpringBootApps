package com.luv2code.springmvc;

import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.repository.StudentDao;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application.properties")
@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class StudentAndGradeServiceTest {
    @Autowired
    private StudentAndGradeService studentService;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    JdbcTemplate jdbc;

    @BeforeEach
    public void setupDatabase(){
        jdbc.execute("insert into student(id, firstname, lastname, email_address)" +
                "values(1, 'Anaya', 'Banerjee', 'anaya.banerjee@gmail.com')");
    }

    @DisplayName("Student Service Test")
    @Test
    public void createStudentService(){
        studentService.createStudent("Anaya", "Banerjee", "anaisha.banerjee@gmail.com");
        CollegeStudent student = studentDao.findByEmailAddress("anaisha.banerjee@gmail.com");
        assertEquals("anaisha.banerjee@gmail.com", student.getEmailAddress(), "find by email");
    }

    @Test
    public void isStudentNullCheck(){
        assertTrue(studentService.checkIfStudentIsNull(1));
        assertFalse(studentService.checkIfStudentIsNull(0));
    }

    @Test
    public void deleteStudentService(){
        Optional<CollegeStudent> deletedCollegeStudent = studentDao.findById(1);
        assertTrue(deletedCollegeStudent.isPresent(), "Return True");
        studentService.deleteStudent(1);
        deletedCollegeStudent = studentDao.findById(1);
        assertFalse(deletedCollegeStudent.isPresent(), "Return False");
    }

    @Test
    @Sql("/insert-data.sql")
    public void getGradeBookService(){
        Iterable<CollegeStudent> collegeStudentIterator = studentService.getGradeBook();

        List<CollegeStudent> collegeStudentList = new ArrayList<>();

        for (CollegeStudent collegeStudent : collegeStudentIterator){
            collegeStudentList.add(collegeStudent);
        }

        assertEquals(5, collegeStudentList.size());
    }

    @AfterEach
    public void setupAfterTransaction(){
        jdbc.execute("DELETE from student");
    }
}
