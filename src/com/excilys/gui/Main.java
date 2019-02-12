package com.excilys.gui;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.excilys.persistance.dao.CompanyDao;
import com.excilys.persistance.dao.ComputerDao;
import com.excilys.persistance.model.Company;
import com.excilys.persistance.model.Computer;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		ComputerDao computerDao = new ComputerDao();
		CompanyDao companyDao = new CompanyDao();
		
		// Prints the menu.
		System.out.println("Faites votre choix :\n "
				+ "1 / List Computers\n "
				+ "2 / List Companies\n "
				+ "3 / Show Details\n "
				+ "4 / Create a Computer\n "
				+ "5 / Update a Computer\n "
				+ "6 / Delete a Computer\n ");
		
		// Handle user input.
		int number = Integer.parseInt(readUserInput(input));
		
		switch(number){
			case 1: 
				// Show the computer list.
				System.out.println("--- Computer List ---");	
				List<Computer> computerList = computerDao.getAll();
				if(computerList != null) {
					for(Computer computer: computerList) {
						System.out.println(computer);
					}
				}
				break;
			case 2: 
				// Show the company list.
				System.out.println("--- Company List ---");
				List<Company> companyList = companyDao.getAll();
				if(companyList != null) {
					for(Company company: companyList) {
						System.out.println(company);
					}
				}
				break;
			case 3:
				// Show a selection interface between computers and company.
				System.out.println("--- Show Details ---\n"
						+ "1 / Computer\n"
						+ "2 / Company");
				// Select the id of the item.
				int detailSelection = Integer.parseInt(readUserInput(input));
				System.out.println("Which one do you want to display?");
				long idSelection = Long.parseLong(readUserInput(input));
				switch(detailSelection) {
					case 1:
						// Return the computer with the selected id.
						try {
							Optional<Computer> computer = computerDao.get(idSelection);
							System.out.println(computer.get());
						} catch(IndexOutOfBoundsException e) {
							System.out.println("This index do not exist [" + e.getMessage() + "]");
						}
						break;
					case 2:
						// Return the company with the selected id.
						try {
							Optional<Company> company = companyDao.get(idSelection);
							System.out.println(company.get());
						} catch(IndexOutOfBoundsException e) {
							System.out.println("This index do not exist [" + e.getMessage() + "]");
						}
						break;
				}
				break;
			case 4:
				// Get new Computer from user input.
				System.out.println("--- New Computer creation ---\n"
						+ "Enter computer\'s name :\n");
				String nameInput = readUserInput(input);
				
				// Create the new Computer object.
				Computer computer = new Computer();
				computer.setName(nameInput);
				computerDao.save(computer);
				break;
			case 5:
				System.out.println("--- Update a computer ---\n"
						+ "Which computer do you want to update?");
				long idUpdateSelector = Long.parseLong(readUserInput(input));
				// Preview the selected computer to update.
				try {
					Optional<Computer> updateComputer = computerDao.get(idUpdateSelector);
					System.out.println(updateComputer.get());
				} catch(IndexOutOfBoundsException e) {
					System.out.println("This index do not exist [" + e.getMessage() + "]");
				}
				System.out.println("Are you sure you want to update this computer? [y/N]");
				break;
			case 6:
				System.out.println("--- Delete a computer ---\n"
						+ "Which computer do you want to delete?");
				// Preview the selected computer to delete.
				long idDeletionSelector = Long.parseLong(readUserInput(input));
				try {
					Optional<Computer> deleteComputer = computerDao.get(idDeletionSelector);
					System.out.println(deleteComputer.get());
					System.out.println("Are you sure you want to delete this computer? [y/N]");
					char idDeletionValidator = readUserInput(input).charAt(0);
					if(idDeletionValidator == 'y') {
						computerDao.delete(deleteComputer.get());	
					} else {
						System.out.println("Mission aborted!");
					}
				} catch(IndexOutOfBoundsException e) {
					System.out.println("This index do not exist [" + e.getMessage() + "]");
				}
				break;
			default:
				break;
		}
		
		input.close();
	}
	
	private static String readUserInput(Scanner scan) {
		return scan.nextLine();
	}
}
