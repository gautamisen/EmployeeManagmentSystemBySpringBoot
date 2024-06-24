package com.emp.service;

import java.util.List;

import com.emp.model.Employees;

public interface EmployeeService {
	public List<Employees> EmployeeList();
	
	public Employees saveEmployee(Employees emp);
	
	public Employees findEmployeeById(int id);
	
	public Employees updateEmployee(Employees emp,int id);
	
	public String deleteEmployess(int id);
	
	public Employees findEmployeeByEmailAndPassword(String email,String password);
	
	public Employees findEmployeeByEmail(String email);
}
