package com.employee.management.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.system.service.CompanyService;

/**
 * implements the  company controller for basic operations on company details
 * @author Lakshmi K
 *
 */

@RestController
@RequestMapping("/companies")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	/**
	 * To delete the company based on Id
	 * @param id
	 */
	@RequestMapping(method=RequestMethod.DELETE, path="/{id}")
	public void deleteCompany(@PathVariable int id) {
		companyService.deleteCompany(id);
	}

}
