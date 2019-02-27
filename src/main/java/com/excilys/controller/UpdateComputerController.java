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
import com.excilys.view.UpdateComputerView;

/**
 * The Class UpdateComputerController.
 */
public class UpdateComputerController {

  /** The update computer controller instance. */
  private static UpdateComputerController updateComputerControllerInstance = null;

  /**
   * Instantiates a new update computer controller.
   */
  private UpdateComputerController() {
    UpdateComputerView view = UpdateComputerView.getInstance();
    Validator validator = Validator.getInstance();
    Scanner scan = new Scanner(System.in);

    view.askForId();
    long id = (long) scan.nextInt();
    Computer computer = ComputerService.getInstance().get(id).get();

    try {
      view.askForNewName(computer);
      String nameInput = scan.next();
      validator.validateName(nameInput);
      computer.setName(nameInput);

      view.askForNewIntroduced(computer);
      String introducedInput = scan.next();
      validator.validateDate(introducedInput);
      Date introducedDate = new SimpleDateFormat("dd/MM/yyyy").parse(introducedInput);

      view.askForNewDiscontinued(computer);
      String discontinuedInput = scan.next();
      validator.validateDate(discontinuedInput);
      Date discontinuedDate = new SimpleDateFormat("dd/MM/yyyy").parse(discontinuedInput);
      validator.validatePrecedence(introducedDate, discontinuedDate);
      computer.setIntroduced(introducedDate);
      computer.setDiscontinued(discontinuedDate);

      view.askForNewCompany(computer);
      long companyInput = (long) scan.nextInt();
      Optional<Company> company = DaoFactory.getInstance().getCompanyDao().get(companyInput);
      
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
   * Gets the single instance of UpdateComputerController.
   *
   * @return single instance of UpdateComputerController
   */
  public static UpdateComputerController getInstance() {
    if (updateComputerControllerInstance == null) {
      updateComputerControllerInstance = new UpdateComputerController();
    }
    return updateComputerControllerInstance;
  }
}
