package com.employee.management.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.system.exceptions.ResourceNotFoundException;
import com.employee.management.system.resource.Company;
import com.employee.management.system.resource.Employee;
import com.employee.management.system.service.SearchEmployeeService;

/**
 * 
 * implements the  employee search controller for basic and extended search based on fields
 * @author Lakshmi K
 * 
 *
 */
@RestController
@RequestMapping("/employees")
public class SearchEmployeesController {

	@Autowired
	private SearchEmployeeService searchEmployeeService;

	/**
	 * Endpoint for extended employee search
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param companyName
	 * @param companyId
	 * @return the map of employees grouped by company
	 */
	@GetMapping("/search-extend")
	public List<Employee> searchEmployeesExtended(@RequestParam(required = false) Integer id,
			@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName,
			@RequestParam(required = false) String companyName,
			@RequestParam(required = false) Integer companyId) {

		List<Employee> employeeList = searchEmployeeService.searchEmployeesExtend(id,firstName,lastName,companyName,companyId);
		if(employeeList.isEmpty())
			throw new ResourceNotFoundException("No results found for the search data");
		return employeeList;
	}

	/**
	 * end-point to search employees by any of the "Employee" table fields
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @return the list of employees
	 */
	@GetMapping("/search")
	public List<Employee> searchEmployees(@RequestParam(required = false) Integer id,
			@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName) {

		List<Employee> searchList = searchEmployeeService.searchEmployees(id,firstName,lastName);
		if(searchList.isEmpty())
			throw new ResourceNotFoundException("No results found for the search data");
		return searchList;
	}

}
