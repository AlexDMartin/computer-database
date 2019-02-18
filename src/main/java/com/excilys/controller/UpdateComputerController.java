package com.excilys.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.LoggerFactory;

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Company;
import com.excilys.dao.model.Computer;
import com.excilys.view.UpdateComputerView;

public class UpdateComputerController {
	private static UpdateComputerController updateComputerControllerInstance = null;

	private UpdateComputerController() {

		UpdateComputerView view = UpdateComputerView.getInstance();
		Validator validator = Validator.getInstance();
		Scanner scan = new Scanner(System.in);

		view.askForId();

		long id = (long) scan.nextInt();
		Computer computer = DaoFactory.getInstance().getComputerDao().get(id).get();

		view.askForNewName(computer);

		String nameInput = scan.next();

		view.askForNewIntroduced(computer);

		String introducedInput = scan.next();

		view.askForNewDiscontinued(computer);

		String discontinuedInput = scan.next();

		boolean dateValidated = false;
		Date introducedDate = null;
		Date discontinuedDate = null;

		try {
			introducedDate = new SimpleDateFormat("dd/MM/yyyy").parse(introducedInput);
			discontinuedDate = new SimpleDateFormat("dd/MM/yyyy").parse(discontinuedInput);
			dateValidated = validator.precedence(introducedDate, discontinuedDate);
		} catch (Exception e) {
			LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
		}

		view.askForNewCompany(computer);

		long companyInput = (long) scan.nextInt();
		Optional<Company> company = DaoFactory.getInstance().getCompanyDao().get(companyInput);
		boolean companyExists = validator.companyExists(company);

		if (dateValidated && companyExists) {
			computer.setName(nameInput);
			computer.setIntroduced(introducedDate);
			computer.setDiscontinued(discontinuedDate);
			computer.setCompany(company.get());

			DaoFactory.getInstance().getComputerDao().update(computer);
		}
		scan.close();
	}

	public static UpdateComputerController getInstance() {
		if (updateComputerControllerInstance == null) {
			updateComputerControllerInstance = new UpdateComputerController();
		}
		return updateComputerControllerInstance;
	}
}
