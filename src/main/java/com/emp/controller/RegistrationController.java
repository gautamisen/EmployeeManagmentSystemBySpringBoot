package com.emp.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.emp.model.Employees;
import com.emp.service.EmployeeServiceImp;

@Controller
public class RegistrationController {
	@Autowired
	EmployeeServiceImp empServiceImpl;

	@GetMapping("/register")
	public String register(Model m) {
		m.addAttribute("employee", new Employees());
		return "registation";
	}

	@PostMapping("/register")
	public String doRegister(@ModelAttribute("employee")Employees emp,Model m)
	{
		String name = emp.getName();
		String password = emp.getPassword();
		String email = emp.getEmail();
		String address = emp.getAddress();
		String salary = emp.getSalary();
		
		if(name.isEmpty())
		{
			m.addAttribute("error", "Name is required");
			return"registation";
		}
		else if(email.isEmpty()) {
			m.addAttribute("error", "Email is required");
			m.addAttribute("name", name);
			return "registation";
		}
		
		else if(password.isEmpty())
		{
			m.addAttribute("error", "Password is required");
			m.addAttribute("name", name);
			m.addAttribute("email", email);
			return"registation";
		}
		else if(address.isEmpty())
		{
			m.addAttribute("error", "Address is required");
			m.addAttribute("name", name);
			m.addAttribute("email", email);
			m.addAttribute("password", password);
			return "registation";
		}
		else if(salary.isEmpty())
		{
			m.addAttribute("error", "Salary is required");
			m.addAttribute("name", name);
			m.addAttribute("email", email);
			m.addAttribute("password", password);
			m.addAttribute("address", address);
			return "registation";
		}
		else if(empServiceImpl.findEmployeeByEmail(email) !=null) {
			m.addAttribute("error", "Email is Already Taken");
			m.addAttribute("name", name);
			m.addAttribute("email", email);
			m.addAttribute("password", password);
			m.addAttribute("address", address);
			m.addAttribute("salary", salary);
			return "registation";
		}
		else
		{	
			emp.setCreated(new Date());
			emp.setModified(new Date());
			emp.setStatus(1);
			Employees saveEmployee = empServiceImpl.saveEmployee(emp);
			if(saveEmployee == null)
			{
				m.addAttribute("error","Something went Wrong");
				m.addAttribute("name", name);
				m.addAttribute("email", email);
				m.addAttribute("password", password);
				m.addAttribute("address", address);
				m.addAttribute("salary", salary);
				return "registation";
			}
			return "login";
		}
		
		
	}

}
