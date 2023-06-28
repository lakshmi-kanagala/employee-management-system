package com.employee.management.employeemanagementsystem;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.employee.management.system.EmployeeManagementSystemApplication;
import com.employee.management.system.controller.CompanyController;
import com.employee.management.system.service.CompanyService;


@SpringBootTest(classes = EmployeeManagementSystemApplication.class)
public class CompanyControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private CompanyController companyController;

    @BeforeEach
    public void setup(WebApplicationContext wac) {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
    }

    @Test
    public void testDeleteCompany_Success() throws Exception {
        // Arrange
        int companyId = 1;
        doNothing().when(companyService).deleteCompany(companyId);

        // Act
        MvcResult result = mockMvc.perform(delete("/companies/{id}", companyId))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        verify(companyService).deleteCompany(companyId);
    }

    @Test
    public void testDeleteCompany_NonExistentId() throws Exception {
        // Arrange
        int companyId = 999; // Assuming non-existent ID
        doNothing().when(companyService).deleteCompany(companyId);

        // Act
         mockMvc.perform(delete("/companies/{id}", companyId))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        verify(companyService).deleteCompany(companyId);
    }

    @Test
    public void testDeleteCompany_InvalidId() throws Exception {
        // Arrange
        String invalidId = "ABC"; // Invalid ID data type
        doNothing().when(companyService).deleteCompany(anyInt());

        // Act
        mockMvc.perform(delete("/companies/{id}", invalidId))
                .andExpect(status().isBadRequest())
                .andReturn();

        // Assert
        verify(companyService).deleteCompany(anyInt());
    }


}
