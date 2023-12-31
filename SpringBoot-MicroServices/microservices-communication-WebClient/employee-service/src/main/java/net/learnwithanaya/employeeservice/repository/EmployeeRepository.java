package net.learnwithanaya.employeeservice.repository;

import net.learnwithanaya.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
