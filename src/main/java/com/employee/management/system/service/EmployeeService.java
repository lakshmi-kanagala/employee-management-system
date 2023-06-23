package com.employee.management.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.system.exceptions.ResourceNotFoundException;
import com.employee.management.system.exceptions.TableDataEmptyException;
import com.employee.management.system.repository.CompanyRepository;
import com.employee.management.system.repository.EmployeeRepository;
import com.employee.management.system.resource.Company;
import com.employee.management.system.resource.Employee;

import jakarta.validation.Valid;

/**
 * Employee service class to perform business logic and operations on employee data
 * @author Lakshmi K
 *
 */
@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CompanyRepository companyRepository;
	/**
	 * To fetch all employee details from employee table
	 * @return
	 */
	public List<Employee> findAll() {
		List<Employee> employeeList = employeeRepository.findAll();
		if(employeeList.isEmpty()) 
			throw new TableDataEmptyException("No employees in database");
		return employeeList;
	}

	public void deleteById(int id) {
		employeeRepository.deleteById(id);
	}
	/**
	 * To save or update the employee into database
	 * @param employee
	 * @return
	 */
	public Employee save(@Valid Employee employee) {

		// Set the company for the employee
		Integer companyId = employee.getCompany().getId();
		Company company = companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company not found"));
		employee.setCompany(company);

		return employeeRepository.save(employee);
	}




}
