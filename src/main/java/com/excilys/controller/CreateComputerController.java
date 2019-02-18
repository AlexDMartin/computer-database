package com.excilys.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.LoggerFactory;

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Company;
import com.excilys.dao.model.Computer;
import com.excilys.view.CreateComputerView;

public class CreateComputerController {
	private static CreateComputerController createComputerControllerInstance = null;

	private CreateComputerController() {
		CreateComputerView view = CreateComputerView.getInstance();
		Validator validator = Validator.getInstance();
		
		view.askForName();
		
		Scanner scan = new Scanner(System.in);
		String nameInput = scan.next();
		
		view.askForIntroduced();
		
		String introducedInput = scan.next();
		
		view.askForDiscontinued();
		
		String discontinuedInput = scan.next();
		
		boolean dateValidated = false;
		Date introducedDate = null;
		Date discontinuedDate = null;
		
		try {
			introducedDate = new SimpleDateFormat("dd/MM/yyyy").parse(introducedInput);
			discontinuedDate = new SimpleDateFormat("dd/MM/yyyy").parse(discontinuedInput);
			dateValidated = validator.precedence(introducedDate, discontinuedDate);	
		} catch(Exception e) {
			LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
		}
		
		view.askForCompany();
		
		long companyInput = (long) scan.nextInt();
		Optional<Company> company = DaoFactory.getInstance().getCompanyDao().get(companyInput); 
		boolean companyExists = validator.companyExists(company);
		
		scan.close();
		
		if(dateValidated && companyExists) {
			Computer computer = new Computer();
			computer.setName(nameInput);
			computer.setIntroduced(introducedDate);
			computer.setDiscontinued(discontinuedDate);
			computer.setCompany(company.get());
			
			DaoFactory.getInstance().getComputerDao().save(computer);
		}		
	}

	public static CreateComputerController getInstance() {
		if (createComputerControllerInstance == null) {
			createComputerControllerInstance = new CreateComputerController();
		}
		return createComputerControllerInstance;
	}
}
