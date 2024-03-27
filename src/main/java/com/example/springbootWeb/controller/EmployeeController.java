package com.example.springbootWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springbootWeb.model.Employee;
import com.example.springbootWeb.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService myEmployeeService;
	
	//display list of employees
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1,model);
//		model.addAttribute("listEmployees", myEmployeeService.getAllEmployees());
//			return "index";	
	}
	
	@GetMapping("/ShowNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		//create model attribute to bind form data
		Employee employee= new Employee();
		model.addAttribute("employee", employee);
			return "new_employee";	
	}
	
	@PostMapping("/saveEmployee")
	public String addEmployee(@ModelAttribute("employee") Employee employee) {
		// save employee to database
		this.myEmployeeService.addEmployee(employee);
		return "redirect:/";
	}
	
	@GetMapping("/ShowFormForUpdate/{id}")
	public String updateEmployee(@PathVariable(value="id") long id,Model model) {
		//get employee from the service 
		Employee employee = myEmployeeService.getEmployeeByiD(id);
		
		//set employee as a model attribute to pre-populate the from
		model.addAttribute("employee", employee);
		return "update_Employee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value="id") long id,Model model) {
		//call delete employee method
		this.myEmployeeService.deleteEmployeeById(id);
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageNO}")
	public String findPaginated(@PathVariable(value="pageNO") int pageNO, Model model) {
		int pageSize=5;
		Page<Employee> page = myEmployeeService.findPaginated(pageNO, pageSize);
		List<Employee> listEmployees = page.getContent();
		
		model.addAttribute("currentPage", pageNO);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listEmployees", listEmployees);
		return "index";
	}
		
}
