package com.excilys.controller;

import com.excilys.dao.mappers.CompanyMapper;
import com.excilys.dao.mappers.ComputerMapper;
import com.excilys.dao.model.Company;
import com.excilys.dao.model.Computer;
import com.excilys.dto.CompanyDto;
import com.excilys.dto.ComputerDtoBuilder;
import com.excilys.exception.validation.ValidationException;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.validation.CompanyValidation;
import com.excilys.validation.ComputerValidation;
import com.excilys.view.CreateComputerView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton implementation of CreateComputerController.
 */
public class CreateComputerController {

  /** Singleton implementation of CreateComputerController. */
  private static CreateComputerController createComputerControllerInstance = null;
  /** Computer Mapper. */
  private static ComputerMapper computerMapper = ComputerMapper.getInstance();
  /** Company Mapper. */
  private static CompanyMapper companyMapper = CompanyMapper.getInstance();
  /** View. */
  private static CreateComputerView view = CreateComputerView.getInstance();
  /** ComputerValidator. */
  private static ComputerValidation computerValidation = ComputerValidation.getInstance();
  /** CompanyValidator. */
  private static CompanyValidation companyValidation = CompanyValidation.getInstance();
  /** ComputerService. */
  private static ComputerService computerService = ComputerService.getInstance();
  /** CompanyService. */
  private static CompanyService companyService = CompanyService.getInstance();
  /** Scanner. */
  private static Scanner scan = new Scanner(System.in);
  /** Logger. */
  private static Logger logger = LoggerFactory.getLogger(CreateComputerController.class);

  /**
   * CreateComputerController Constructor.
   */
  private CreateComputerController() {
    Computer computer = null;
    try {
      ComputerDtoBuilder computerDtoBuilder = new ComputerDtoBuilder();

      view.askForName();
      String nameInput = scan.next();
      computerValidation.validateName(nameInput);
      computerDtoBuilder.addName(nameInput);

      view.askForIntroduced();
      String introducedInput = scan.next();
      computerValidation.validateIntroductionDate(introducedInput);
      Date introducedDate = null;
      if (introducedInput != null) {
        computerDtoBuilder.addIntroduced(introducedInput);
        introducedDate = new SimpleDateFormat("yyyy-MM-dd").parse(introducedInput);
      }

      view.askForDiscontinued();
      String discontinuedInput = scan.next();
      computerValidation.validateDiscontinuationDate(discontinuedInput);
      Date discontinuedDate = null;
      if (discontinuedInput != null) {
        computerDtoBuilder.addDiscontinued(discontinuedInput);
        discontinuedDate = new SimpleDateFormat("yyyy-MM-dd").parse(discontinuedInput);
      }
      computerValidation.validatePrecedence(introducedDate, discontinuedDate);

      view.askForCompany();
      long companyInput = (long) scan.nextInt();
      Optional<Company> company = companyService.get(companyInput);
      companyValidation.validateId(company.get().getId());
      companyValidation.validateName(company.get().getName());
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
