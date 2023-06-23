package com.employee.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.management.system.resource.Company;
/**
 * Repository for Company entity to perform DB operations
 * @author Lakshmi K
 *
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{

}
