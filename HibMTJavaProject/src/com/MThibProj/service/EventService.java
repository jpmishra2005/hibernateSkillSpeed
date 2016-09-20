package com.MThibProj.service;

import org.hibernate.Session;

import com.MThibProj.dao.EmployeeDao;
import com.MThibProj.dao.EventDao;
import com.MThibProj.entity.Employee;
import com.MThibProj.entity.Event;
import com.MThibProj.exceptions.NoSuchEmployeeFoundException;
import com.MThibProj.exceptions.NoSuchEventFoundException;

public class EventService {
	
	EventDao eventDao=new EventDao();
	EmployeeDao employeeDao=new EmployeeDao();
	public void addEvent(String eventTitle, String eventDescription, Session session) {
		Event event =new Event();
		event.setDescription(eventDescription);
		event.setEventTitle(eventTitle);
		eventDao.registerEvent(event,session);
		System.out.println("Event Persisted Successfully");
	}

	public void registerEventToEmployee(String employeeID, String eventName, Session session) {
		Employee emp = null;
		Event event = null;
		try {
			emp = employeeDao.findEmployeeByEmpID(employeeID,session);
	
			event = eventDao.findEvent(eventName,session);
			if((emp !=null )&&(event !=null)){
				emp.addEvent(event);
			}
			
			employeeDao.updateEmp(emp,session);
			System.out.println("Event is registered to employee successfully");
		} 
		catch (NoSuchEmployeeFoundException e) {
			System.out.println(e.getMessage());
		}
		catch (NoSuchEventFoundException e) {
			System.out.println(e.getMessage());
		}
		
		
	}


}
