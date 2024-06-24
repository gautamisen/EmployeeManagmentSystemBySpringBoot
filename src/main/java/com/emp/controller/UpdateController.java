package com.emp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.emp.model.Employees;
import com.emp.service.EmployeeServiceImp;

@Controller
public class UpdateController{
	@Autowired
	EmployeeServiceImp empService;
	
	
	@GetMapping("/update/{id}")
	public String updateEmployee(@PathVariable("id") int id , Model m)
	{	
		Employees findEmployeeById = empService.findEmployeeById(id);
		m.addAttribute("employee", findEmployeeById);
		
		return "update";
	}
	@PostMapping("/update/{id}")
	public String updateEmployee(@PathVariable("id") int id, @ModelAttribute("employee") Employees emp, Model m)
	{
		String name = emp.getName();
		String address = emp.getAddress();
		String email = emp.getEmail();
		String password = emp.getPassword();
		String salary = emp.getSalary();
		
		Employees findEmployeeById = empService.findEmployeeById(id);
		
		if(name.isEmpty())
		{
			m.addAttribute("error", "Name is required");
			m.addAttribute("employee", findEmployeeById);
			return"update";
		}
		else if(address.isEmpty())
		{
			m.addAttribute("error", "Address is required");
		
			m.addAttribute("employee", findEmployeeById);
			return"update";
		}
		
		else if(email.isEmpty())
		{
			m.addAttribute("error", "Email is required");
		
			m.addAttribute("employee", findEmployeeById);
			return"update";
		}
		else if(password.isEmpty())
		{
			m.addAttribute("error", "Password is required");
		
			m.addAttribute("employee", findEmployeeById);
			return"update";
		}
		else if(salary.isEmpty())
		{
			m.addAttribute("error", "Salary is required");
		
			m.addAttribute("employee", findEmployeeById);
			return"update";
		}
		else
		{
			Employees updateEmployee = empService.updateEmployee(emp, id);
			if(updateEmployee == null) {
				m.addAttribute("error", "Something Went wrong?????");
				m.addAttribute("employee", findEmployeeById);
				return"update";
			}
			else {
				return"redirect:/home";
			}
		}
		
		
	}
	

	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable("id") int id)
	{	
		empService.deleteEmployess(id);
		return "redirect:/home";
	}
}
