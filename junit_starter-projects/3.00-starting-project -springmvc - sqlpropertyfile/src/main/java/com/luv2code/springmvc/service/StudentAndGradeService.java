package com.luv2code.springmvc.service;

import com.luv2code.springmvc.models.*;
import com.luv2code.springmvc.repository.HistoryGradesDao;
import com.luv2code.springmvc.repository.MathGradesDao;
import com.luv2code.springmvc.repository.ScienceGradesDao;
import com.luv2code.springmvc.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentAndGradeService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private MathGradesDao mathGradesDao;

    @Autowired
    @Qualifier("mathGrades")
    private MathGrade mathGrade;

    @Autowired
    @Qualifier("scienceGrades")
    private ScienceGrade scienceGrade;

    @Autowired
    private ScienceGradesDao scienceGradesDao;

    @Autowired
    @Qualifier("historyGrades")
    private HistoryGrade historyGrade;

    @Autowired
    private HistoryGradesDao historyGradesDao;

    @Autowired
    private StudentGrades studentGrades;


    public void createStudent(String firstName, String lastName, String emailAddress) {
        CollegeStudent student = new CollegeStudent(firstName, lastName, emailAddress);
        student.setId(0);
        studentDao.save(student);
    }

    public boolean checkIfStudentIsNull(int id) {
        Optional<CollegeStudent> student = studentDao.findById(id);
        if (student.isPresent()) {
            return true;
        }
        return false;
    }

    public void deleteStudent(int id) {
        if (checkIfStudentIsNull(id)) {
            studentDao.deleteById(id);
            mathGradesDao.deleteByStudentId(id);
            scienceGradesDao.deleteByStudentId(id);
            historyGradesDao.deleteByStudentId(id);
        }
    }

    public Iterable<CollegeStudent> getGradeBook() {
        Iterable<CollegeStudent> collegeStudents = studentDao.findAll();
        return collegeStudents;
    }

    public boolean createGarde(double grade, int studentId, String gradeType) {

        if (!checkIfStudentIsNull(studentId))
            return false;
        if (grade >= 0 && grade <= 100) {
            if (gradeType.equalsIgnoreCase("math")) {
                mathGrade.setId(0);
                mathGrade.setGrade(grade);
                mathGrade.setStudentId(studentId);
                mathGradesDao.save(mathGrade);
                return true;
            }
            if (gradeType.equalsIgnoreCase("science")) {
                scienceGrade.setId(0);
                scienceGrade.setGrade(grade);
                scienceGrade.setStudentId(studentId);
                scienceGradesDao.save(scienceGrade);
                return true;
            }
            if (gradeType.equalsIgnoreCase("history")) {
                historyGrade.setId(0);
                historyGrade.setGrade(grade);
                historyGrade.setStudentId(studentId);
                historyGradesDao.save(historyGrade);
                return true;
            }
        }
        return false;
    }

    public int deleteGrade(int id, String gradeType) {
        int studentId =0;
        if (gradeType.equalsIgnoreCase("math")){
            Optional<MathGrade> grade = mathGradesDao.findById(id);
            if(!grade.isPresent()){
                return studentId;
            }
            else{
                studentId = grade.get().getStudentId();
                mathGradesDao.deleteById(id);
                return studentId;
            }
        }
        if (gradeType.equalsIgnoreCase("science")){
            Optional<ScienceGrade> grade = scienceGradesDao.findById(id);
            if(!grade.isPresent()){
                return studentId;
            }
            else{
                studentId = grade.get().getStudentId();
                scienceGradesDao.deleteById(id);
                return studentId;
            }
        }
        if (gradeType.equalsIgnoreCase("history")){
            Optional<HistoryGrade> grade = historyGradesDao.findById(id);
            if(!grade.isPresent()){
                return studentId;
            }
            else{
                studentId = grade.get().getStudentId();
                historyGradesDao.deleteById(id);
                return studentId;
            }
        }
        return studentId;
    }

    public GradebookCollegeStudent studentInformation(int id) {
        if(!checkIfStudentIsNull(id)){
            return null;
        }
        Optional<CollegeStudent> collegeStudent = studentDao.findById(id);

        if(!collegeStudent.isPresent()){
            return null;
        }

        Iterable<MathGrade> mathGrades = mathGradesDao.findGradeByStudentId(id);
        Iterable<ScienceGrade> scienceGrades = scienceGradesDao.findGradeByStudentId(id);
        Iterable<HistoryGrade> historyGrades = historyGradesDao.findByStudentId(id);

        List<Grade> mathGradeList = new ArrayList<>();
        mathGrades.forEach(mathGradeList::add);

        List<Grade> scienceGradeList = new ArrayList<>();
        scienceGrades.forEach(scienceGradeList::add);

        List<Grade> historyGradeList = new ArrayList<>();
        historyGrades.forEach(historyGradeList::add);

        studentGrades.setMathGradeResults(mathGradeList);
        studentGrades.setScienceGradeResults(scienceGradeList);
        studentGrades.setHistoryGradeResults(historyGradeList);

        GradebookCollegeStudent gradebookCollegeStudent = new GradebookCollegeStudent(collegeStudent.get().getId(),
                collegeStudent.get().getFirstname(), collegeStudent.get().getLastname(), collegeStudent.get().getEmailAddress(),
                studentGrades);

        return gradebookCollegeStudent;
    }
}
