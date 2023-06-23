package com.employee.management.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.management.system.resource.Employee;

/**
 * repository for employee entity to perform DB operations
 * @author Lakshmi K
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	List<Employee> findByIdOrFirstNameOrLastNameOrderByFirstNameAsc(Integer id, String firstName, String lastName);


	@Query("SELECT e FROM Employee e " +
			"WHERE (:id IS NULL OR e.id = :id) " +
			"AND (:firstName IS NULL OR LOWER(e.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) " +
			"AND (:lastName IS NULL OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) " +
			"AND (:companyName IS NULL OR LOWER(e.company.name) LIKE LOWER(CONCAT('%', :companyName, '%'))) " +
			"AND (:companyId IS NULL OR e.company.id = :companyId) " +
			"ORDER BY e.company.name, e.firstName")
	List<Employee> searchEmployeesExtended(@Param("id") Integer id,
			@Param("firstName") String firstName,
			@Param("lastName") String lastName,
			@Param("companyName") String companyName,
			@Param("companyId") Integer companyId);


}
