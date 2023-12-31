package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ReflectionTestUtilTest {

    @Autowired
    CollegeStudent studentOne;

    @Autowired
    ApplicationContext context;

    @Autowired
    StudentGrades studentGrades;

    @BeforeEach
    public void beforeEach(){
        studentOne.setFirstname("Anaya");
        studentOne.setLastname("Banerjee");
        studentOne.setEmailAddress("anaya.banerjee@gmail.com");
        studentOne.setStudentGrades(studentGrades);

        ReflectionTestUtils.setField(studentOne, "id", 1);
        ReflectionTestUtils.setField(studentOne, "studentGrades", new StudentGrades(new ArrayList<>(
                Arrays.asList(100.0, 85.0, 76.50, 91.75))));
    }

    @DisplayName("Get Data from Private Field")
    @Test
    public void getDataFromPrivateField(){
        assertEquals(1, ReflectionTestUtils.getField(studentOne,"id"));
    }

    @DisplayName("Test Provate Method")
    @Test
    public void invokePrivateMethod(){
        assertEquals("Anaya 1",
                ReflectionTestUtils.invokeMethod(studentOne, "getFirstNameAndId"),
        "Fail private method not call");
    }

}