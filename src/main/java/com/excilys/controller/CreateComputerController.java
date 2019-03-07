package com.excilys.controller;

import com.excilys.dao.mappers.CompanyMapper;
import com.excilys.dao.mappers.ComputerMapper;
import com.excilys.dao.model.Company;
import com.excilys.dao.model.Computer;
import com.excilys.dto.CompanyDto;
import com.excilys.dto.ComputerDtoBuilder;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.validation.Validation;
import com.excilys.view.CreateComputerView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;
import javax.xml.bind.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton implementation of CreateComputerController.
 */
public class CreateComputerController {

  /** Singleton implementation of CreateComputerController. */
  private static CreateComputerController createComputerControllerInstance = null;
  /** Computer Mapper. */
  ComputerMapper computerMapper = ComputerMapper.getInstance();
  /** Company Mapper. */
  CompanyMapper companyMapper = CompanyMapper.getInstance();
  /** View. */
  CreateComputerView view = CreateComputerView.getInstance();
  /** Validator. */
  Validation validator = Validation.getInstance();
  /** ComputerService. */
  ComputerService computerService = ComputerService.getInstance();
  /** CompanyService. */
  CompanyService companyService = CompanyService.getInstance();
  /** Scanner. */
  Scanner scan = new Scanner(System.in);
  /** Logger. */
  Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * CreateComputerController Constructor.
   */
  private CreateComputerController() {
    Computer computer = null;
    try {
      ComputerDtoBuilder computerDtoBuilder = new ComputerDtoBuilder();

      view.askForName();
      String nameInput = scan.next();
      validator.validateName(nameInput);
      computerDtoBuilder.addName(nameInput);

      view.askForIntroduced();
      String introducedInput = scan.next();
      validator.validateDate(introducedInput);
      Date introducedDate = null;
      if (introducedInput != null) {
        computerDtoBuilder.addIntroduced(introducedInput);
        introducedDate = new SimpleDateFormat("yyyy-MM-dd").parse(introducedInput);
      }

      view.askForDiscontinued();
      String discontinuedInput = scan.next();
      validator.validateDate(discontinuedInput);
      Date discontinuedDate = null;
      if (discontinuedInput != null) {
        computerDtoBuilder.addDiscontinued(discontinuedInput);
        discontinuedDate = new SimpleDateFormat("yyyy-MM-dd").parse(discontinuedInput);
      }
      validator.validatePrecedence(introducedDate, discontinuedDate);

      view.askForCompany();
      long companyInput = (long) scan.nextInt();
      Optional<Company> company = companyService.get(companyInput);
      validator.validateCompany(company.get());
      CompanyDto companyDto = null;
      if (company.isPresent()) {
        companyDto = (CompanyDto) companyMapper.entityToDto(company.get());
        computerDtoBuilder.addCompanyDto(companyDto);
      }

      computer = computerMapper.dtoToEntity(computerDtoBuilder.build());

      scan.close();
    } catch (ValidationException e) {
      logger.warn(e.getMessage());
    } catch (ParseException e) {
      logger.warn(e.getMessage());
    }

    try {
      if (computer != null) {
        computerService.save(computer);
      }
    } catch (Exception e) {
      logger.warn(e.getMessage());
    }
  }

  /**
   * Singleton implementation of CreateComputerController.
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
