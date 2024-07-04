package com.eden.dao;

import java.util.List;

import com.eden.entity.Employee;

public interface EmployeeDao {

	void save(Employee employee);

	List<Employee> lists();

	Employee findById(Integer id);

	void update(Employee employee);

	void delete(Integer id);

	List<Employee> searchEmp(String searchName, Double salaryBegin, Double salaryEnd, String dateBegin, String dateEnd);

	



	

}
