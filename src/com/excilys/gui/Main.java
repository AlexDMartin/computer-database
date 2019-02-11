package com.excilys.gui;

import java.util.Scanner;

import com.excilys.persistance.dao.CompanyDao;
import com.excilys.persistance.dao.ComputerDao;

public class Main {
	public static void main(String[] args) {
		
		// Prints the menu.
		System.out.println("Faites votre choix :\n "
				+ "1 / List Computers\n "
				+ "2 / List Companies\n "
				+ "3 / Show Details\n "
				+ "4 / Create a Computer\n "
				+ "5 / Update a Computer\n "
				+ "6 / Delete a Computer\n ");
		
		// Tests.
//		CompanyDao companydao = new CompanyDao();
//		companydao.getAll();
		
		// Handle user input.
		Scanner input = new Scanner(System.in);
		int number = input.nextInt();
		
		switch(number){
			case 1: 
				System.out.println("Computer List :");
				ComputerDao computerDao = new ComputerDao();
				computerDao.getAll();
				System.out.println("--------------");
				break;
			case 2: 
				System.out.println("Company List :");
				CompanyDao companyDao = new CompanyDao();
				companyDao.getAll();
				System.out.println("--------------");
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6: 
				break;
			default:
				break;
		}
	}
}
