package com.emp.controller;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.emp.service.EmployeeServiceImp;



@Controller
public class HomeController {

	@Autowired
	EmployeeServiceImp empService;
	
	@GetMapping("/home")
	public String home(Model m) {
		m.addAttribute("emplist", empService.EmployeeList());
		return "home";
	}
	
	@GetMapping("/service")
	public String service() {
		return "service";
	}
	
	@GetMapping("/aboutus")
	public String aboutus()
	{
		return"aboutus";
	}
	@GetMapping("/career")
	public String career()
	{
		
		return"career";
	}
	@PostMapping("/carrer")
	public String career(@RequestBody String req, Model m)
	{
		String[] split = req.split("&");
		Arrays.asList(split)
			  .forEach((t)->{System.out.println(t);});
		m.addAttribute("success", "Application Send..");
		return "career";
	}
}
