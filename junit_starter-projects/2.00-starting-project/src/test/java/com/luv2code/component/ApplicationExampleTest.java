package com.luv2code.component;

import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.Student;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest(classes = MvcTestingExampleApplication.class) // test and app package name is different then add
// (classes = SprintbootAppclassName.class)
@SpringBootTest
public class ApplicationExampleTest {

    private static int count = 0;
    @Value("${info.app.name}")
    private String appInfo;
    @Value("${info.school.name}")
    private String schoolName;
    @Value("${info.app.description}")
    private String appDescription;
    @Value("${info.app.version}")
    private String appVersion;

    @Autowired
    CollegeStudent student;

    @Autowired
    StudentGrades studentGrades;

    @Autowired
    ApplicationContext context;

    @BeforeEach
    public void beforeEach(){
        count = count +1;
        System.out.println("Testing: " + appInfo + " which is: " + appDescription +
                " Version: " + appVersion + ". Execution of most test method " + count);
        student.setFirstname("Lakme");
        student.setLastname("India");
        student.setEmailAddress("a.b@test.com");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0, 85.0, 76.50, 91.75)));
        student.setStudentGrades(studentGrades);
    }

    @DisplayName("Add grade results for Student grades")
    @Test
    public void addGradeResultsForStudentGrades(){
        assertEquals(353.25, studentGrades.addGradeResultsForSingleClass
                (student.getStudentGrades().getMathGradeResults()), "Grade Addition matches");
    }

    @DisplayName("Add grade results for student grades not equal")
    @Test
    public void addGradeResultsForStudentGardesAssertNotEquals(){
        assertNotEquals(0, studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults()), "Grades does not match");
    }

    @DisplayName("Is grade greater")
    @Test
    public void isGradeGreaterStudentGrades(){
        assertTrue(studentGrades.isGradeGreater(90.0, 80.0), "failure-should be true");
    }

    @DisplayName("Is grade greater false")
    @Test
    public void isGradeGreaterStudentGradesAssertFalse(){
        assertFalse(studentGrades.isGradeGreater(80.0, 90.0), "failure-should be false");
    }

    @DisplayName("Check null for student grades")
    @Test
    public void isStudentGradeNull(){
        assertNotNull(studentGrades.checkNull(student.getStudentGrades().getMathGradeResults()), "Object should not be null");
    }

    @DisplayName("Create student without grade in it")
    @Test
    public void createStudentWithoutGradeInIt(){
        CollegeStudent student2 = context.getBean("collegeStudent", CollegeStudent.class);
        student2.setFirstname("Anaya");
        student2.setLastname("Banerjee");
        student2.setEmailAddress("anaya.banerjee@gmail.com");
        assertNotNull(student2.getFirstname());
        assertNotNull(student2.getLastname());
        assertNotNull(student2.getEmailAddress());
        assertNull(studentGrades.checkNull(student2.getStudentGrades()));
    }

    @DisplayName("Verify Students are prototype")
    @Test
    public void verifyStudentsArePrototype(){
        Student student2 = context.getBean("collegeStudent", Student.class);
        assertNotSame(student, student2);
    }

    @DisplayName("Find the grade point average")
    @Test
    public void findGradePointAverage(){
       // assertEquals(353.25, studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults()));

        //assertEquals(88.31, studentGrades.findGradePointAverage(student.getStudentGrades().getMathGradeResults()));
    //Combine two/all assertEquals together
        assertAll("Testing all assertEquals",
                () -> assertEquals(353.25, studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults())),
                () -> assertEquals(88.31, studentGrades.findGradePointAverage(student.getStudentGrades().getMathGradeResults())));

    }

    @Test
    void basicTest(){

    }
}