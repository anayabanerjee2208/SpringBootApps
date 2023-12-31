package com.luv2code.springmvc;

import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.models.GradebookCollegeStudent;
import com.luv2code.springmvc.repository.StudentDao;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.scheduling.aspectj.AnnotationAsyncExecutionAspect;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
public class GradeBookControllerTest {

    private static MockHttpServletRequest request;
    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    StudentDao studentDao;

    @Mock
    private StudentAndGradeService studentCreateServiceMock;

    @Value("${sql.scripts.create.student}")
    private String sqlAddStudent;

    @Value("${sql.script.create.math.grade}")
    private String sqlAddMathGrade;

    @Value("${sql.script.create.science.grade}")
    private String sqlAddScienceGrade;

    @Value("${sql.script.create.history.grade}")
    private String sqlAddHistoryGrade;

    @Value("${sql.scripts.delete.student}")
    private String sqlDeleteStudent;

    @Value("${sql.scripts.delete.math}")
    private String sqlDeleteMathGrade;

    @Value("${sql.scripts.delete.science}")
    private String sqlDeleteScienceGrade;

    @Value("${sql.scripts.delete.history}")
    private String sqlDeleteHistoryGrade;

    @BeforeAll //Berforeall must always be static, then any field inside the method needs to be declared as static
    public static void setup(){
        request = new MockHttpServletRequest();
        request.setParameter("firstname", "Anaya");
        request.setParameter("lastname", "Banerjee");
        request.setParameter("emailAddress", "anaisha.banerjee@gmail.com");
    }

    @BeforeEach
    public void setupDatabase() {
        jdbc.execute(sqlAddStudent);
        jdbc.execute(sqlAddMathGrade);
        jdbc.execute(sqlAddScienceGrade);
        jdbc.execute(sqlAddHistoryGrade);
    }

    @AfterEach
    public void setupAfterTransaction() {
        jdbc.execute(sqlDeleteStudent);
        jdbc.execute(sqlDeleteMathGrade);
        jdbc.execute(sqlDeleteScienceGrade);
        jdbc.execute(sqlDeleteHistoryGrade);
    }

    @Test
    public void getStudentHttpRequest() throws Exception {
        CollegeStudent studentOne = new GradebookCollegeStudent("Binni", "Singh", "binni@gmail.com");
        CollegeStudent studentTwo = new GradebookCollegeStudent("Anaisha", "Singh", "anaisha@gmail.com");
        List<CollegeStudent> collegeStudentList = new ArrayList<>(Arrays.asList(studentOne, studentTwo));
        when(studentCreateServiceMock.getGradeBook()).thenReturn(collegeStudentList);
        assertIterableEquals(collegeStudentList, studentCreateServiceMock.getGradeBook());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk()).andReturn();
        ModelAndView mvc = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(mvc, "index");
    }

    @Test
    public void createStudentHttpRequest() throws Exception {
        CollegeStudent studentOne = new CollegeStudent("Eric", "Roby", "eric_roby@test.com");
        List<CollegeStudent> collegeStudentsList = new ArrayList<>(Arrays.asList(studentOne));

        when(studentCreateServiceMock.getGradeBook()).thenReturn(collegeStudentsList);

        assertIterableEquals(collegeStudentsList, studentCreateServiceMock.getGradeBook());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON).param("firstname", request.getParameterValues("firstname"))
                .param("lastname", request.getParameterValues("lastname"))
                .param("emailAddress", request.getParameterValues("emailAddress")))
                .andExpect(status().isOk()).andReturn();
        ModelAndView mvc = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(mvc, "index");

        CollegeStudent student = studentDao.findByEmailAddress("anaisha.banerjee@gmail.com");
        assertNotNull(student, "Studen should be found");
    }

    @Test
    public void deleteStudentHttpRequestTest() throws Exception {
        assertTrue(studentDao.findById(1).isPresent());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/delete/student/{id}", 1))
                .andExpect(status().isOk()).andReturn();
        ModelAndView mav = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(mav, "index");

        assertFalse(studentDao.findById(1).isPresent());
    }

    @Test
    public void deleteStudentHttpRequestErrorPage() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/delete/student/{id}", 0))
                .andExpect(status().isOk()).andReturn();
        ModelAndView mav = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(mav, "error");
    }

}
