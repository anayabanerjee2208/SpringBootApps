package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.dao.ApplicationDao;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import com.luv2code.component.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class MockAnnotationTest {

    @Autowired
    CollegeStudent studentOne;

    @Autowired
    ApplicationContext context;

    @Autowired
    StudentGrades studentGrades;

    @Mock
    private ApplicationDao applicationDao;

    @InjectMocks
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
}
