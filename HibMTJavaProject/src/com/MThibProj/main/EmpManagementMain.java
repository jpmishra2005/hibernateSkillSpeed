package com.MThibProj.main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.MThibProj.service.EmployeeService;
import com.MThibProj.service.EventService;

public class EmpManagementMain {


	private static EmployeeService employeeService=new EmployeeService();
	private static EventService eventService=new EventService();
	private static Session session;

	/* Setting up Hibernate & create session factory*/
	public static void setup(){
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder();
		srBuilder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
		SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
		session=factory.openSession();
	}
	@SuppressWarnings("deprecation")
	private static void registerEmployee(){

		System.out.println("Employee ID?");
		String empID=sc.nextLine();
		System.out.println("Employee Name?");
		String empName=sc.nextLine();
		System.out.println("JoinDate (dd/MM/yyyy)?");
		String joinDate=sc.nextLine();
		System.out.println("Enter Email ID");
		String email=sc.nextLine();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
		Date startDate=null;
		try {
			startDate= (Date)formatter.parse(joinDate);
		} catch (ParseException e) {
			System.out.println("Date format is wrong");

		} 
		employeeService.addEmployee(empID, empName, startDate, email,session);
	}

	@SuppressWarnings("deprecation")
	private static void registerEvent(){
		System.out.println("Enter Event Title");
		String eventTitle=sc.nextLine();
		System.out.println("Enter Event Description");
		String eventDescription=sc.nextLine();

		eventService.addEvent(eventTitle,eventDescription,session);
	}


	@SuppressWarnings("deprecation")
	private static void mapEventToEmployees(){
		System.out.println("Enter Employee ID");
		String employeeID=sc.nextLine();
		System.out.println("Enter Event Name");
		String eventName=sc.nextLine();

		eventService.registerEventToEmployee(employeeID,eventName,session);
	}


	private static Scanner sc=new Scanner(System.in);
	private static void showMenu(){
		System.out.println("Select from the following options");
		System.out.println("-------------------------------------------------");
		System.out.println("1. Register New Employee ");
		System.out.println("2. Register New Event");
		System.out.println("3. Map employee to event");
		System.out.println("4. List all employees");
		System.out.println("5. Exit");
		System.out.println("-------------------------------------------------");
	}

	private static void listAllEmployees(){
		employeeService.showAllEmployees(session);
	}

	public static void main(String[] args) {

		setup();
		int choice;
		do{
			EmpManagementMain.showMenu();
			choice=Integer.parseInt(sc.nextLine());
			switch(choice){
			case 1: EmpManagementMain.registerEmployee();
			break;
			case 2:EmpManagementMain.registerEvent();
			break;
			case 3:EmpManagementMain.mapEventToEmployees();
			break;
			case 4:EmpManagementMain.listAllEmployees();
			break;
			}
		}while(choice !=5);

	}

}
