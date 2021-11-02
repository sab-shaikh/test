package com.hcl.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hcl.entity.Employee;

public class EmployeeDaoImpl implements EmployeeDao{

	Configuration cfg;
	SessionFactory factory;
	Session session;
	Transaction transaction;
	
	Scanner scan = new Scanner(System.in);

	public void connection()
	{
		cfg     = new Configuration().configure("hibernate/config/employee_cfg.xml");
		factory = cfg.buildSessionFactory();
		session = factory.openSession();
		transaction = session.beginTransaction();
	}
	
	public void addEmployee() {
		connection();
		System.out.println("Enter size of employee=");		
		int size = scan.nextInt();
      
        ArrayList<Employee> list = new ArrayList<Employee>();
        for(int i=0; i< size; i++)
        {
        	System.out.println("Enter empid=");
        	int empId = scan.nextInt();
        	scan.nextLine();
        	System.out.println("Enter emp name=");
        	String empName = scan.nextLine();
        	System.out.println("enter emp salary=");
        	int empSalary = scan.nextInt();
        	list.add(new Employee(empId, empName, empSalary));
        }
        for(Employee emp : list) {
            session.save(emp);
        }
	///	session.save(value);
		System.out.println("data saved in database...");
		transaction.commit();
		session.close();
	}

	public void deleteEmployee() {
		connection();
		Query query = session.createQuery("delete from Employee where empName=:emp_name" );
		query.setParameter("emp_name","John");
		int r = query.executeUpdate();
		System.out.println(r+" record delete...");
		transaction.commit();
		session.close();	
	}

	public void showEmployee() {
		connection();
		Query query = session.createQuery("from Employee" );
		List<Employee> listEmp = query.list();
		for(Employee empListData : listEmp)
		{
			System.out.println(empListData.getEmpId()+"\t"+empListData.getEmpName()+"\t"+empListData.getEmpSalary());
		}
		transaction.commit();
		session.close();	
	}

	public void updateEmployee() {
		connection();
		Query query = session.createQuery("update Employee set empName=:emp_name where empId=:emp_Id");
		query.setParameter("emp_name", "Jack");
		query.setParameter("emp_Id", 111);
		int result = query.executeUpdate();
		System.out.println(result+" updated successfully....");
		transaction.commit();
		session.close();
	}

}
