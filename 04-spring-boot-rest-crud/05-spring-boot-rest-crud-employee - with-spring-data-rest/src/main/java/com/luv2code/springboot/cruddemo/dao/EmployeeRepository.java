package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//JpaRepository<EntityType, PrimaryKey>

/**
 * Spring data jpa creates nedpoints based on entity type. First char of entity type is in lowercase
 * then add "s" to the entity. So here it would be "/employees"
 * It cannot handle complex plural name,like Goose-> Geese, in that case manually specify the plural name
 * By default spring data rest will return first 20elements
 */

/**
 * sort endpoint?sort=<entityname>,desc
 * http://localhost:8080/magic-api/members?sort=lastName,desc
 */

//manually specify path name
@RepositoryRestResource(path="members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//that's it, no need to write any code
}
