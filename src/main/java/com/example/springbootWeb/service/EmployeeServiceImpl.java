package com.example.springbootWeb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.springbootWeb.model.Employee;
import com.example.springbootWeb.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Employee employee;
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public void addEmployee(Employee employee) {
		this.employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeByiD(long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		if(optional.isPresent()) {
			this.employee = optional.get();
		}
		else {
			throw new RuntimeException("Employee not found for id :: "+ id);
		}
		return employee;
	}

	@Override
	public void deleteEmployeeById(long id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public Page<Employee> findPaginated(int pageNO, int pageSize) {
		Pageable pageable= PageRequest.of(pageNO-1, pageSize);
		return this.employeeRepository.findAll(pageable);
	}

}
