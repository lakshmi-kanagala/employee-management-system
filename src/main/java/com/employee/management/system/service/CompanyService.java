package com.employee.management.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.system.exceptions.InvalidRequestException;
import com.employee.management.system.exceptions.ResourceNotFoundException;
import com.employee.management.system.repository.CompanyRepository;
import com.employee.management.system.resource.Company;

/**
 * CompanyService class to perform business logic on company data
 * @author Lakshmi K
 *
 */
@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;

	/**
	 * /**
	 * To delete the company based on Id, if they no longer have employees
	 * @param id
	 */
	public void deleteCompany(int id) {

		Company company = companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company not found"));
		if (company.getEmployees().isEmpty()) {
			companyRepository.delete(company);
		} else {
			throw new InvalidRequestException("Company has active employees and cannot be deleted");
		}

	}
}
