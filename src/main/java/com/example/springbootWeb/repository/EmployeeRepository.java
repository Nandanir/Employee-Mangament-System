package com.example.springbootWeb.repository;

import org.springframework.stereotype.Repository;

import com.example.springbootWeb.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}
