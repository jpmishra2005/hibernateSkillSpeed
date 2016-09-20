package com.MThibProj.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import com.MThibProj.entity.Employee;
import com.MThibProj.exceptions.NoSuchEmployeeFoundException;

public class EmployeeDao {

public List<Employee> listAllEmps(Session session) {
		
		Transaction tx=null;
		List<Employee> employeeList=null;

		tx = session.beginTransaction();

		Criteria cr = session.createCriteria(Employee.class);
		employeeList=cr.list();
		
		tx.commit();
		return employeeList;
	}
	public void registerEmployee(Employee emp, Session session){

		Transaction tx=null;
		try{
			tx = session.beginTransaction();

			session.save(emp);
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error in persisting the data in database");

		}
		tx.commit();
	


	}
	public Employee findEmployeeByEmpID(String empID, Session session) throws NoSuchEmployeeFoundException {

	
		Transaction tx=null;
		Employee employee=null;

		tx = session.beginTransaction();

		Criteria cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.eq("mid", empID));
		employee=(Employee) cr.uniqueResult();
		if(employee==null){
			throw new NoSuchEmployeeFoundException("No such employee found");

		}
		tx.commit();
		
		return employee;


	}
	public void updateEmp(Employee emp,Session session) {
	
		Transaction tx=null;
		try{
			tx = session.beginTransaction();

			session.update(emp);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		tx.commit();
	


	}
	
}
