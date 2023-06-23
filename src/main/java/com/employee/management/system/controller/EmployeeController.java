package com.employee.management.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	public List<Employee> getEmployees() {
		return employeeService.findAll();
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
	public Employee createOrUpdateEmployee(@Valid @RequestBody Employee employee) {
		return employeeService.save(employee);
	}

}
