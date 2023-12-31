package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.dao.ApplicationDao;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import com.luv2code.component.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Documented;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class WithoutMockAnnotationTest {
    @Autowired
    CollegeStudent studentOne;

    @Autowired
    ApplicationContext context;

    @Autowired
    StudentGrades studentGrades;

    @MockBean //provided by springboot and not Mockito
    private ApplicationDao applicationDao;

    @Autowired
    private ApplicationService applicationService;

    @BeforeEach
    public void beforeEach(){
        studentOne.setFirstname("Anaya");
        studentOne.setLastname("Banerjee");
        studentOne.setEmailAddress("anaya.banerjee@gmail.com");
        studentOne.setStudentGrades(studentGrades);
    }
    @DisplayName("When & Verify")
    @Test
    public void assertEqualsTestAndGrades(){
        when(applicationDao.addGradeResultsForSingleClass(studentGrades.getMathGradeResults()))
                .thenReturn(100.00);

        assertEquals(100.00,
                applicationService.addGradeResultsForSingleClass(studentOne.getStudentGrades().getMathGradeResults()));

        verify(applicationDao, times(1)).addGradeResultsForSingleClass(studentGrades.getMathGradeResults());
    }

    @DisplayName("Find Gpa")
    @Test
    public void assertEqualsTestFindGpa(){
        when(applicationDao.findGradePointAverage(studentGrades
                .getMathGradeResults())).thenReturn(80.00);

        assertEquals(80.00, applicationService
                .findGradePointAverage(studentOne.getStudentGrades().getMathGradeResults()));

        verify(applicationDao, times(1)).findGradePointAverage(studentGrades.getMathGradeResults());
    }

    @DisplayName("Check not null")
    @Test
    public void testAssertNotNull(){
        when(applicationDao.checkNull(studentGrades.getMathGradeResults())).thenReturn(true);

        assertNotNull(applicationService.checkNull(studentOne.getStudentGrades().getMathGradeResults()),
                "Object should not be null");
    }

    @DisplayName("Throw Runtime Exception")
    @Test
    public void throwRuntimeError(){
        CollegeStudent collegeStudent = (CollegeStudent) context.getBean("collegeStudent");

        doThrow(new RuntimeException()).when(applicationDao).checkNull(collegeStudent);

        assertThrows(RuntimeException.class, () -> {
            applicationService.checkNull(collegeStudent);
        });

        verify(applicationDao, times(1)).checkNull(collegeStudent);
    }

    @DisplayName("Multiple Stubbing")
    @Test
    public void stubbingConsecutiveCalls(){
        CollegeStudent collegeStudent = (CollegeStudent) context.getBean("collegeStudent");

        when(applicationDao.checkNull(collegeStudent)).thenThrow(new RuntimeException())
                .thenReturn("Do not throw exception second time");

        assertThrows(RuntimeException.class, () -> {
            applicationService.checkNull(collegeStudent);
        });

        assertEquals("Do not throw exception second time", applicationService.checkNull(collegeStudent));

        verify(applicationDao, times(2)).checkNull(collegeStudent);
    }

}
