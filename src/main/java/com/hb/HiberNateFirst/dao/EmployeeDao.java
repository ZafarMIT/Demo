package com.hb.HiberNateFirst.dao;

import java.util.List;

import com.hb.HiberNateFirst.pojo.Employee;

public interface EmployeeDao {

	boolean addEmp(Employee emp);
	boolean  updateEmp(Employee emp);
	List<Employee> selectAll();
}
