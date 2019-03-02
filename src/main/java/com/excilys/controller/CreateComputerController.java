package com.excilys.controller;

import com.excilys.dao.mappers.CompanyMapper;
import com.excilys.dao.mappers.ComputerMapper;
import com.excilys.dao.model.Company;
import com.excilys.dao.model.Computer;
import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTOBuilder;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.validator.Validator;
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
  Validator validator = Validator.getInstance();
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
      ComputerDTOBuilder computerDTOBuilder = new ComputerDTOBuilder();

      view.askForName();
      String nameInput = scan.next();
      validator.validateName(nameInput);
      computerDTOBuilder.addName(nameInput);

      view.askForIntroduced();
      String introducedInput = scan.next();
      validator.validateDate(introducedInput);
      Date introducedDate = null;
      if (introducedInput != null) {
        computerDTOBuilder.addIntroduced(introducedInput);
        introducedDate = new SimpleDateFormat("yyyy-MM-dd").parse(introducedInput);
      }

      view.askForDiscontinued();
      String discontinuedInput = scan.next();
      validator.validateDate(discontinuedInput);
      Date discontinuedDate = null;
      if (discontinuedInput != null) {
        computerDTOBuilder.addDiscontinued(discontinuedInput);
        discontinuedDate = new SimpleDateFormat("yyyy-MM-dd").parse(discontinuedInput);
      }
      validator.validatePrecedence(introducedDate, discontinuedDate);

      view.askForCompany();
      long companyInput = (long) scan.nextInt();
      Optional<Company> company = companyService.get(companyInput);
      validator.validateCompany(company.get());
      CompanyDTO companyDTO = null;
      if (company.isPresent()) {
        companyDTO = (CompanyDTO) companyMapper.entityToDTO(company.get());
        computerDTOBuilder.addCompanyDTO(companyDTO);
      }

      computer = computerMapper.DTOToEntity(computerDTOBuilder.build());

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
