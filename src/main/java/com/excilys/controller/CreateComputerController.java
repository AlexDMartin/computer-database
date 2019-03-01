package com.excilys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

import javax.xml.bind.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dao.mappers.CompanyMapper;
import com.excilys.dao.mappers.ComputerMapper;
import com.excilys.dao.model.Company;
import com.excilys.dao.model.Computer;
import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.dto.ComputerDTOBuilder;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.validator.Validator;
import com.excilys.view.CreateComputerView;

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
  /** CompanyService. */
  CompanyService companyService = CompanyService.getInstance();
  /** Scanner. */
  Scanner scan = new Scanner(System.in);
  /** Logger */
  Logger logger = LoggerFactory.getLogger(this.getClass());
  
  /**
   * CreateComputerController Constructor.
   */
  private CreateComputerController() {
    Computer computer = null;
    ComputerDTO computerDTO = null;
    try {
      ComputerDTOBuilder computerDTOBuilder = new ComputerDTOBuilder();
            
      view.askForName();
      String nameInput = scan.next();
      computerDTOBuilder.addName(nameInput);

      view.askForIntroduced();
      String introducedInput = scan.next();
      if(introducedInput != null) {        
        computerDTOBuilder.addIntroduced(introducedInput);
      }

      view.askForDiscontinued();
      String discontinuedInput = scan.next();
      if(discontinuedInput != null) {        
        computerDTOBuilder.addDiscontinued(discontinuedInput);
      }

      view.askForCompany();
      long companyInput = (long) scan.nextInt();        
      Optional<Company> company = companyService.get(companyInput);
      CompanyDTO companyDTO = null;
      if(company.isPresent()) {        
        companyDTO = (CompanyDTO) companyMapper.entityToDTO(company.get());
        computerDTOBuilder.addCompanyDTO(companyDTO);
      }
  
      validator.validateName(nameInput);
      validator.validateDate(introducedInput);
      validator.validateDate(discontinuedInput);
      validator.validateCompany(company.get());
      
      Date introducedDate = new SimpleDateFormat("dd/MM/yyyy").parse(introducedInput);
      Date discontinuedDate = new SimpleDateFormat("dd/MM/yyyy").parse(discontinuedInput);
      validator.validatePrecedence(introducedDate, discontinuedDate);
    
      computerDTO = computerDTOBuilder
      .addName(nameInput)
      .addIntroduced(introducedInput)
      .addDiscontinued(discontinuedInput)
      .addCompanyDTO(companyDTO)
      .build();
      
      computer = computerMapper.DTOToEntity(computerDTO);

      scan.close();
    } catch (ValidationException e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    } catch (ParseException e) {
      LoggerFactory.getLogger(this.getClass()).warn("Failed to parse date");
    }

    try {
      if(computer != null) {
        ComputerService.getInstance().save(computer);
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
