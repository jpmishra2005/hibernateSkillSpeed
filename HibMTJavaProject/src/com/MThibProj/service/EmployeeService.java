package com.MThibProj.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.MThibProj.dao.EmployeeDao;
import com.MThibProj.entity.Employee;
import com.MThibProj.exceptions.NoSuchEmployeeFoundException;

public class EmployeeService {
	
	EmployeeDao empDao=new EmployeeDao();
	public void addEmployee(String empID,String empName,Date joinDate,String emailID, Session session){
		Employee emp=new Employee(empID, emailID, joinDate, empName);
		empDao.registerEmployee(emp,session);
		System.out.println("Employee Persisted Successfully");
	}
	
	public Employee findEmployeeByEmployeeID(String empID,Session session) throws NoSuchEmployeeFoundException{
		Employee emp=empDao.findEmployeeByEmpID(empID,session);
		return emp;
	}

	public void showAllEmployees(Session session) {
		List<Employee> employeeList=empDao.listAllEmps(session);
		for(Employee emp:employeeList)
		{
			System.out.println(emp.toString());
		}
	}

}
