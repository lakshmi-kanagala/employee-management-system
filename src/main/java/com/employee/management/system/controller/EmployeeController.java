package com.employee.management.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.system.exceptions.TableDataEmptyException;
import com.employee.management.system.resource.Employee;
import com.employee.management.system.service.EmployeeService;

import jakarta.validation.Valid;

/**
 * implements the  employee controller for basic operations on employee details
 * @author Lakshmi K
 *
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	/**
	 * Endpoint to get the employees
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getEmployees() {
		List<Employee> employeeList = employeeService.findAll();
		if(employeeList.isEmpty()) 
			throw new TableDataEmptyException("No employees in database");
		return ResponseEntity.ok(employeeList);
	}

	/**
	 * Endpoint to delete an employee
	 * @param id
	 */
	@RequestMapping(method = RequestMethod.DELETE,value="/{id}")
	public void deleteEmployee(@PathVariable int id) {
		employeeService.deleteById(id) ;
	}

	/**
	 *  Endpoint to create or update an employee
	 * @param employee
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Employee> createOrUpdateEmployee(@Valid @RequestBody Employee employee) {
		Employee employeeData = employeeService.save(employee);
		return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(employeeData);
		
	}
}
