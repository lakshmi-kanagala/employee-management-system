package com.employee.management.system.resource;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
/**
 * Entity class for Employee
 * @author Lakshmi K
 *
 */
@Entity
public class Employee {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "first_name")
	@Pattern(regexp = "^[A-Za-z' -]+$", message = "Invalid first name")
	private String firstName;
	@Size(min = 2, message = "Name should be atleast 2 characters")
	@Pattern(regexp = "^[A-Za-z' -]+$", message = "Invalid last name")
	@Column(name = "last_name")
	private String lastName;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", company=" + company
				+ "]";
	}


}
