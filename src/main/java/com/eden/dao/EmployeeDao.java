package com.eden.dao;

import java.util.List;

import com.eden.entity.Employee;

public interface EmployeeDao {

	void save(Employee employee);

	List<Employee> lists();

	Employee findById(Integer id);

	

}
