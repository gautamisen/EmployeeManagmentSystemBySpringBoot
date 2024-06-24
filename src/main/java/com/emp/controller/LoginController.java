package com.emp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.emp.model.Employees;
import com.emp.service.EmployeeServiceImp;

@Controller
public class LoginController {
	@Autowired
	EmployeeServiceImp empServiceImpl;
	
	@GetMapping("/")
	public String login(Model m) {
		m.addAttribute("employee", new Employees());
		return "login";
	}

	@PostMapping("/login")
	public String dologin(@ModelAttribute("employee") Employees emp, Model m) {
		String email = emp.getEmail();
		String password = emp.getPassword();
		if (email.isEmpty()) {
			m.addAttribute("error", "Email is required");
			return "login";
		} else if (password.isEmpty()) {
			m.addAttribute("error", "Password is required");
			m.addAttribute("email", email);

			return "login";
		} 
		
		else {
			Employees findEmployeeByEmailAndPassword = empServiceImpl.findEmployeeByEmailAndPassword(email, password);
			if(findEmployeeByEmailAndPassword == null)
			{
				m.addAttribute("error", "Invalid username or password");
				m.addAttribute("email", email);
				m.addAttribute("password", password);
				return "login";
				
			}
			m.addAttribute("emplist", empServiceImpl.EmployeeList());
			System.out.println(empServiceImpl.EmployeeList());
			return "home";
		}
		
		
		
	}
	

}
