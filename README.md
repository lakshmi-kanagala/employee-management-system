Employee Management System is an application to manage employees at different companies.

Please find the below end point urls:

1. To get all employees : GET localhost:8085/employees
2. To create or update employees in system : POST localhost:8085/employees

sample data for employee object:

   {
        "firstName": "David",
        "lastName": "Roy",
        "company": {
            "id": 1001,
            "name": "Kineo"
        }
    }
    
3. To delete employee by id : GET localhost:8085/employees/{id}

4.  To delete company by id : GET localhost:8085/companies/{id} 

5. To search the employee by any of the employee field : GET localhost:8085/employees/search?id={id} or 
                                                         GET localhost:8085/employees/search?firstName={firstName} or
                                                         GET localhost:8085/employees/search?lastName={lastName}
6.To extend the above search : 

Example url:  GET localhost:8085/employees/search-extend?companyId={companyId}&id={id}