package com.employee.management.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.system.repository.EmployeeRepository;
import com.employee.management.system.resource.Company;
import com.employee.management.system.resource.Employee;
/**
 * To perform serch operations on Employee data
 * @author Lakshmi K
 *
 */
@Service
public class SearchEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	/**
	 * to search employee by Company Name or Company Id. Update the endpoint to first group employees by company, then order by first name alphabetically
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param companyName
	 * @param companyId
	 * @return
	 */
	public Map<Company, List<Employee>> searchEmployeesExtend(Integer id, String firstName, String lastName,
			String companyName, Integer companyId) {

		// Perform extended employee search based on the provided parameters
		List<Employee> employees= employeeRepository.searchEmployeesExtended(id, firstName, lastName, companyName, companyId);

		// Organize the employees by company in a map
		Map<Company, List<Employee>> employeesByCompany = new HashMap<>();
		for (Employee employee : employees) {
			employeesByCompany.computeIfAbsent(employee.getCompany(), k -> new ArrayList<>()).add(employee);
		}
		return employeesByCompany;
	}
	/**
	 *  To search employees by any of the "Employee" table fields 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public List<Employee> searchEmployees(Integer id, String firstName, String lastName) {

		// Perform basic employee search based on the provided parameters

		List<Employee> employees = employeeRepository.findByIdOrFirstNameOrLastNameOrderByFirstNameAsc(id, firstName, lastName);
		return employees;
	}

}
