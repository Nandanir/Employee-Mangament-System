package com.example.springbootWeb.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.springbootWeb.model.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmployees();
	
	void addEmployee(Employee employee);
	
	Employee getEmployeeByiD(long id);
	
	void deleteEmployeeById(long id);
	
	Page<Employee> findPaginated(int pageNO,int pageSize);

}
