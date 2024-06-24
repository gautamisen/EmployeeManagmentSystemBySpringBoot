package com.emp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.model.Employees;
import com.emp.repository.EmployeeRepository;

@Service
public class EmployeeServiceImp implements EmployeeService {
	@Autowired
	 EmployeeRepository empRep;

	@Override
	public List<Employees> EmployeeList() {
		// TODO Auto-generated method stub
		
		return empRep.findAll();
	}

	@Override
	public Employees saveEmployee(Employees emp) {
		// TODO Auto-generated method stub
		return empRep.save(emp);
	}

	@Override
	public Employees findEmployeeById(int id) {
		// TODO Auto-generated method stub
		List<Employees> employeeList = EmployeeList();
		Employees e=null;
		for(Employees emp:employeeList)
		{
			if(emp.getId()==id)
			{
				e=emp;
			}
		}
		return e;
	}

	@Override
	public Employees updateEmployee(Employees emp, int id) {
		// TODO Auto-generated method stub
		Employees oldEmployee = findEmployeeById(id);
		oldEmployee.setName(emp.getName());
		oldEmployee.setEmail(emp.getEmail());
		oldEmployee.setAddress(emp.getAddress());
		oldEmployee.setPassword(emp.getPassword());
		oldEmployee.setSalary(emp.getSalary());
		
		oldEmployee.setModified(new Date());
		
		Employees save = empRep.save(oldEmployee);
		
		return save;
	}

	@Override
	public String deleteEmployess(int id) {
		// TODO Auto-generated method stub
		Employees findEmployeeById = findEmployeeById(id);
		empRep.delete(findEmployeeById);
		return "yes";
	}

	@Override
	public Employees findEmployeeByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return empRep.findEmployeeByEmailAndPassword(email, password);
	}

	@Override
	public Employees findEmployeeByEmail(String email) {
		// TODO Auto-generated method stub
		return empRep.findEmployeesByEmail(email);
	}
	

}
