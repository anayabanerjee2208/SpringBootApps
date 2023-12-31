package com.luv2code.cruddemo;

import com.luv2code.dao.StudentDao;
import com.luv2code.dao.StudentDaoImpl;
import com.luv2code.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.luv2code"})
@EntityScan(basePackages = "com.luv2code.entity")
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDao studentDao){
		return runner-> {
			//createStudent(studentDao);
			//createMultipleStudents(studentDao);
			//readStudent(studentDao);
			queryForStudents(studentDao);
			//queryForStudentsByLastName(studentDao);
			//updateStudent(studentDao);
			//updateStudentLastName(studentDao);
			//deleteStudentById(studentDao);
			//deleteMultipleRecords(studentDao);
			//deleteAllRecords(studentDao);
		};
	}

	private void deleteAllRecords(StudentDao studentDao) {
		System.out.println("Delete all records");
		int numberOfRowsDeleted = studentDao.deleteAllRecords();
		System.out.println("Total Rows deleted " + numberOfRowsDeleted);
	}

	private void deleteMultipleRecords(StudentDao studentDao) {
		String lastName = "Doe";
		studentDao.deleteMultipleStudentBasedOnLastName(lastName);
	}

	private void deleteStudentById(StudentDao studentDao) {
		int studentId = 3;
		studentDao.deletebyId(studentId);
	}

	private void updateStudentLastName(StudentDao studentDao) {
		//Set Student Last Name
		Student student = new Student();
		student.setLastName("Singh");
		//Update the last name
		studentDao.updateAllLastName(student);
	}

	private void updateStudent(StudentDao studentDao) {
		//Retrieve student based on id: primary key
		int studentId = 1;
		System.out.println("Get student based on id: "+ studentId);
		Student student = studentDao.findById(studentId);
		//change first name to scooby
		System.out.println("Update the student first name to scooby...");
		student.setFirstName("Scooby");

		//update the student
		studentDao.updateStudent(student);

		//display the updated student
		System.out.println("Updated Student : " + student);
	}

	private void queryForStudentsByLastName(StudentDao studentDao) {
		//get list of students
		List<Student> theStudents = studentDao.findStudentByLastName("Anaya");
		//display list of students
		for (Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDao studentDao) {

		//Get list of students
		List<Student> theStudents = studentDao.findAll();

		//Display list of students
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDao studentDao) {
		//create a student object
		System.out.println("Creating a new student object..");
		Student student = new Student("Anaya", "Singh", "anaya@gmail.com");
		//save the student
		System.out.println("Saving the student object..");
		studentDao.save(student);
		//display id of the saved student
		System.out.println("Display the student object.." + student.getId());
		//retrieve student based on the id:primary key
		System.out.println("Display the student with id.." + student.getId());
		Student myStudent = studentDao.findById(student.getId());
		//display student
		System.out.println("Display the student .." + myStudent);
	}

	private void createMultipleStudents(StudentDao studentDao) {
		//create the student object
		System.out.println("Creating 3 new student object..");
		Student student1 = new Student("John", "Myers", "john@gmail.com");
		Student student2 = new Student("Mary", "Anne", "Anne@gmail.com");
		Student student3 = new Student("Anaya", "Singh", "anaya@gmail.com");
		//save the student object
		System.out.println("Saving new student object..");
		studentDao.save(student1);
		studentDao.save(student2);
		studentDao.save(student3);
	}

	private void createStudent(StudentDao studentDao) {
		//create the student object
		System.out.println("Creating new student object..");
		Student student = new Student("Paul", "Doe", "test@gmail.com");
		//save the student object
		System.out.println("Saving new student object..");
		studentDao.save(student);
		//display the id of the student
		System.out.println("Displaying the Id of new student object.." + student.getId());
	}

}
