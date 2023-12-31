package com.luv2code.springmvc;

import com.luv2code.springmvc.models.*;
import com.luv2code.springmvc.repository.HistoryGradesDao;
import com.luv2code.springmvc.repository.MathGradesDao;
import com.luv2code.springmvc.repository.ScienceGradesDao;
import com.luv2code.springmvc.repository.StudentDao;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application-test.properties")
@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class StudentAndGradeServiceTest {
    @Autowired
    private StudentAndGradeService studentService;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    MathGradesDao mathGradesDao;

    @Autowired
    ScienceGradesDao scienceGradesDao;

    @Autowired
    HistoryGradesDao historyGradesDao;

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

    @BeforeEach
    public void setupDatabase(){
        jdbc.execute(sqlAddStudent);
        jdbc.execute(sqlAddMathGrade);
        jdbc.execute(sqlAddScienceGrade);
        jdbc.execute(sqlAddHistoryGrade);
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
    public void deleteStudentAndAssociatedGradeTest(){
        Optional<CollegeStudent> deletedCollegeStudent = studentDao.findById(1);
        Optional<MathGrade> mathGrade = mathGradesDao.findById(1);
        Optional<ScienceGrade> scienceGrade = scienceGradesDao.findById(1);
        Optional<HistoryGrade> historyGrade = historyGradesDao.findById(1);

        assertTrue(deletedCollegeStudent.isPresent(), "Return True");
        assertTrue(mathGrade.isPresent());
        assertTrue(scienceGrade.isPresent());
        assertTrue(historyGrade.isPresent());

        studentService.deleteStudent(1);


        deletedCollegeStudent = studentDao.findById(1);
        mathGrade = mathGradesDao.findById(1);
        scienceGrade = scienceGradesDao.findById(1);
        historyGrade = historyGradesDao.findById(1);

        assertFalse(deletedCollegeStudent.isPresent(), "Return False");
        assertFalse(mathGrade.isPresent(), "Return False");
        assertFalse(scienceGrade.isPresent(), "Return False");
        assertFalse(historyGrade.isPresent(), "Return False");

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

    @Test
    public void createGradeServiceTest(){

        //Create the grade
        assertTrue(studentService.createGarde(80.50, 1, "math"));
        assertTrue(studentService.createGarde(80.50, 1, "science"));
        assertTrue(studentService.createGarde(80.50, 1, "history"));

        //Get all grades with studentid
        Iterable<MathGrade> mathGrades = mathGradesDao.findGradeByStudentId(1);
        Iterable<ScienceGrade> scienceGrades = scienceGradesDao.findGradeByStudentId(1);
        Iterable<HistoryGrade> historyGrades = historyGradesDao.findByStudentId(1);

        //Verify there is grades
        assertTrue(mathGrades.iterator().hasNext(), "Math grade is 80.50");
        assertTrue(scienceGrades.iterator().hasNext(), "Science grade is 80.50");
        assertTrue(historyGrades.iterator().hasNext(), "history grade is 80.50");

        //verify size of grade
        assertEquals(2, ((Collection<MathGrade>) mathGrades).size());
        assertEquals(2,((Collection<ScienceGrade>) scienceGrades).size());
        assertEquals(2,((Collection<HistoryGrade>) historyGrades).size());
    }

    @Test
    public void createGradeServiceReturnFalse(){
        assertFalse(studentService.createGarde(105, 1, "math"));
        assertFalse(studentService.createGarde(-5, 1, "math"));
        assertFalse(studentService.createGarde(80.50, 2, "math"));
        assertFalse(studentService.createGarde(80.50,1, "literature"));
    }

    @Test
    public void deleteGradeServiceTest(){
        assertEquals(1, studentService.deleteGrade(1, "math"), "returns student id after delete");
        assertEquals(1, studentService.deleteGrade(1, "science"), "returns student id after delete");
        assertEquals(1, studentService.deleteGrade(1, "history"), "returns student id after delete");
    }

    @Test
    public void deleteGardeServiceReturnStudentIdOfZero(){
        assertEquals(0, studentService.deleteGrade(0, "Math"), "No student have 0 as id");
        assertEquals(0, studentService.deleteGrade(1, "Arts"), "No student have a Art class");
    }

    @Test
    public void getStudentInformationTest(){
        GradebookCollegeStudent gradebookCollegeStudent = studentService.studentInformation(1);
        assertNotNull(gradebookCollegeStudent);
        assertEquals(1, gradebookCollegeStudent.getId());
        assertEquals("Anaya", gradebookCollegeStudent.getFirstname());
        assertEquals("Banerjee", gradebookCollegeStudent.getLastname());
        assertEquals("anaya.banerjee@gmail.com", gradebookCollegeStudent.getEmailAddress());
        assertTrue(gradebookCollegeStudent.getStudentGrades().getMathGradeResults().size() == 1);
        assertTrue(gradebookCollegeStudent.getStudentGrades().getHistoryGradeResults().size() == 1);
        assertTrue(gradebookCollegeStudent.getStudentGrades().getScienceGradeResults().size() == 1);

    }

    @Test
    public void studentInformationServiceReturnNullTest(){
        GradebookCollegeStudent gradebookCollegeStudent = studentService.studentInformation(5);
        assertNull(gradebookCollegeStudent);
    }

    @AfterEach
    public void setupAfterTransaction(){

        jdbc.execute(sqlDeleteStudent);
        jdbc.execute(sqlDeleteMathGrade);
        jdbc.execute(sqlDeleteScienceGrade);
        jdbc.execute(sqlDeleteHistoryGrade);
    }
}
