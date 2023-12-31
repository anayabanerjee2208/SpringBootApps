package com.luv2code.dao;

import com.luv2code.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao{

    //Define field for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired
    public StudentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //implement the save method
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {

        //Create query
        TypedQuery<Student> theQuery = entityManager.createQuery("From Student", Student.class);
        TypedQuery<Student> theQuery1 = entityManager.createQuery("From Student order by firstName desc", Student.class);

        //Return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findStudentByLastName(String findByLastName) {

        //Create query
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "From Student where lastName=:theData", Student.class);

        //Set query parameters
        theQuery.setParameter("theData", findByLastName);

        //return query results
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
       entityManager.merge(student);
    }

    @Override
    @Transactional
    public void updateAllLastName(Student student) {
        Query theQuery = entityManager.createQuery("UPDATE Student SET lastName=:theData");
        theQuery.setParameter("theData", student.getLastName());
        int numberOfRowsUpdated = theQuery.executeUpdate();
        //int numberOfRowsUpdated1 = entityManager.createQuery("UPDATE Student SET lastName='Singh'").executeUpdate();
    }

    @Override
    @Transactional
    public void deletebyId(int id) {
        //retrive the student
        Student myStudent = entityManager.find(Student.class, id);

        //delete the student
        entityManager.remove(myStudent);
    }

    @Override
    @Transactional
    public void deleteMultipleStudentBasedOnLastName(String lastName) {
        Query theQuery = entityManager.createQuery("DELETE From Student where lastName=:theData");
        theQuery.setParameter("theData", lastName);
        int numberOfRowsDeleted = theQuery.executeUpdate();
    }

    @Override
    @Transactional
    public int deleteAllRecords() {
        int numberOfRowsDeleted = entityManager.createQuery("DELETE from Student").executeUpdate();
        return numberOfRowsDeleted;
    }
}
