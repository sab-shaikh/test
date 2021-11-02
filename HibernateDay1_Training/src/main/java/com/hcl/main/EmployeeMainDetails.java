package com.hcl.main;

import java.util.Scanner;

import com.hcl.dao.EmployeeDaoImpl;

public class EmployeeMainDetails {
	public static void main(String[] args) {
		EmployeeDaoImpl empDaoImpl = new EmployeeDaoImpl();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter choice \n1.Add Record"+"\n2.Show Record"+"\n3.Delete Record"+"\n4.Update Record");
		int choice = scan.nextInt();
		switch (choice)
		{
			case 1:
					empDaoImpl.addEmployee();	
					break;
			case 2:
					empDaoImpl.showEmployee();	
					break;
			case 3:
					empDaoImpl.deleteEmployee();	
					break;
			case 4:
					empDaoImpl.updateEmployee();	
					break;
			default:
					System.out.println("Invalid choice...");
					break;
		}
	}

}
