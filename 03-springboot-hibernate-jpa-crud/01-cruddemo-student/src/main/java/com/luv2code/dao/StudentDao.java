package com.luv2code.dao;

import com.luv2code.entity.Student;

import java.util.List;

public interface StudentDao {

    void save(Student student);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findStudentByLastName(String lastName);

    void updateStudent(Student student);

    void updateAllLastName(Student student);

    void deletebyId(int id);

    void deleteMultipleStudentBasedOnLastName(String lastName);

    int deleteAllRecords();
}
