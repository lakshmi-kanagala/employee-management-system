package com.employee.management.employeemanagementsystem;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.employee.management.system.EmployeeManagementSystemApplication;
import com.employee.management.system.controller.EmployeeController;
import com.employee.management.system.resource.Employee;
import com.employee.management.system.service.EmployeeService;
import com.employee.management.system.resource.Company;


@SpringBootTest(classes = EmployeeManagementSystemApplication.class)
public class EmployeeControllerTests {

	private MockMvc mockMvc;
	
	@Mock
	private EmployeeService employeeService;
	
	@InjectMocks
	private EmployeeController employeeController;
	
	 @BeforeEach
	    public void setup(WebApplicationContext wac) {
	        MockitoAnnotations.openMocks(this);
	        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	    }
	
	@Test
	public void testFindAll_success() throws Exception {
		
		List<Employee> employeeList = new ArrayList<>();
		Company company1 = new Company();
		company1.setId(1003);
		company1.setName("Kineo");
		Employee emp1 = new Employee(1,"Joe","Johns",company1);
		employeeList.add(emp1);
		
		Company company2 = new Company();
		company2.setId(1004);
		company2.setName("Hugry jacks");
		Employee emp2 = new Employee(2,"Bob","Biden",company2);
		employeeList.add(emp2);
		
		when(employeeService.findAll()).thenReturn(employeeList);
		mockMvc.perform(get("/employees")).andExpect(status().isOk()).andReturn();
		   verify(employeeService).findAll();        
	}
	
	
	
	@Test
	public void testDeleteEmployee_success() throws Exception {
		int id=1;
		doNothing().when(employeeService).deleteById(id);
		
		MvcResult result = mockMvc.perform(delete("/employees/{id}",1)).andExpect(status().isOk()).andReturn();
		verify(employeeService).deleteById(id);
	}
	
	@Test
	public void testDeleteEmployee_nodata() throws Exception {
		int id=2346;
		doNothing().when(employeeService).deleteById(id);
		
		MvcResult result = mockMvc.perform(delete("/employees/{id}",id)).andExpect(status().isOk()).andReturn();
		verify(employeeService).deleteById(id);
	}
	
	@Test
	public void testDeleteEmployee_invalid() throws Exception {
		String id="Employee";
		doNothing().when(employeeService).deleteById(anyInt());
		
		 mockMvc.perform(delete("/employees/{id}",id)).andExpect(status().isBadRequest()).andReturn();
		verify(employeeService).deleteById(anyInt());
	}
	
	@Test
	public void testCreateEmployee_success() throws Exception {
		
		Company company1 = new Company();
		company1.setId(1001);
		company1.setName("Kineo");
		
		
		Employee employee = new Employee(2,"Joe","Johns",company1);
        when(employeeService.save(employee)).thenReturn(employee);
		
		mockMvc.perform(post("/employees",employee)
               .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isCreated());
        verify(employeeService).save(any(Employee.class));

	}

}

