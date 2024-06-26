package com.eden.service;

import java.util.List;

import com.eden.entity.Employee;

public interface EmployeeService {

	void save(Employee employee);

	List<Employee> lists();

	Employee findById(Integer id);

}
