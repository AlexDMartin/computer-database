package com.excilys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

import javax.xml.bind.ValidationException;

import org.slf4j.LoggerFactory;

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Company;
import com.excilys.dao.model.Computer;
import com.excilys.service.ComputerService;
import com.excilys.validator.Validator;
import com.excilys.view.CreateComputerView;

/**
 * The Class CreateComputerController.
 */
public class CreateComputerController {

  /** The create computer controller instance. */
  private static CreateComputerController createComputerControllerInstance = null;

  /**
   * Instantiates a new creates the computer controller.
   */
  private CreateComputerController() {
    CreateComputerView view = CreateComputerView.getInstance();
    Validator validator = Validator.getInstance();
    Computer computer = new Computer();
    Scanner scan = new Scanner(System.in);

    try {
      view.askForName();
      String nameInput = scan.next();
      validator.validateName(nameInput);
      computer.setName(nameInput);

      view.askForIntroduced();
      String introducedInput = scan.next();
      validator.validateDate(introducedInput);
      Date introducedDate = new SimpleDateFormat("dd/MM/yyyy").parse(introducedInput);

      view.askForDiscontinued();
      String discontinuedInput = scan.next();
      validator.validateDate(discontinuedInput);
      Date discontinuedDate = new SimpleDateFormat("dd/MM/yyyy").parse(discontinuedInput);
      validator.validatePrecedence(introducedDate, discontinuedDate);
      computer.setIntroduced(introducedDate);
      computer.setDiscontinued(discontinuedDate);

      view.askForCompany();
      long companyInput = (long) scan.nextInt();
      Optional<Company> company = DaoFactory.getInstance().getCompanyDao().get(companyInput);
      ;
      validator.validateCompany(company);
      computer.setCompany(company.get());

      scan.close();
    } catch (ValidationException e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    } catch (ParseException e) {
      LoggerFactory.getLogger(this.getClass()).warn("Failed to parse date");
    }

    try {
      ComputerService.getInstance().save(computer);
    } catch (Exception e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    }
  }

  /**
   * Gets the single instance of CreateComputerController.
   *
   * @return single instance of CreateComputerController
   */
  public static CreateComputerController getInstance() {
    if (createComputerControllerInstance == null) {
      createComputerControllerInstance = new CreateComputerController();
    }
    return createComputerControllerInstance;
  }
}
