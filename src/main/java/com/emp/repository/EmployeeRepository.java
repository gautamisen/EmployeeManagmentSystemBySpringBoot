package com.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emp.model.Employees;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Integer> {
	
	public Employees findEmployeeByEmailAndPassword(String email,String password);
	
	public Employees findEmployeesByEmail(String email);
	

}
