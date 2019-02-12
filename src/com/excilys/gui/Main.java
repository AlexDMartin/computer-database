package com.excilys.gui;

import java.util.List;
import java.util.Scanner;

import com.excilys.persistance.Company;
import com.excilys.persistance.Computer;
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
		
		// Handle user input.
		Scanner input = new Scanner(System.in);
		int number = input.nextInt();
		input.close();
		
		switch(number){
			case 1: 
				System.out.println("Computer List :");
				ComputerDao computerDao = new ComputerDao();
				List<Computer> computerList = computerDao.getAll();
				if(computerList != null) {
					System.out.println(computerList.get(0));
				}
				System.out.println("--------------");
				break;
			case 2: 
				System.out.println("Company List :");
				CompanyDao companyDao = new CompanyDao();
				List<Company> companyList = companyDao.getAll();
				if(companyList != null) {
					for(Company company: companyList) {
						System.out.println(company);
					}
				}
				System.out.println("--------------");
				break;
			case 3:
				System.out.println("Show Details :");
				break;
			case 4:
				System.out.println("Create a computer :");
				break;
			case 5:
				System.out.println("Update a computer :");
				break;
			case 6:
				System.out.println("Delete a computer :");
				break;
			default:
				break;
		}
	}
}
