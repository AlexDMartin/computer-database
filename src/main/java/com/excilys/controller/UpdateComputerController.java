package com.excilys.controller;

import com.excilys.dao.mappers.CompanyMapper;
import com.excilys.dao.mappers.ComputerMapper;
import com.excilys.dao.model.Company;
import com.excilys.dao.model.Computer;
import com.excilys.dto.CompanyDto;
import com.excilys.dto.ComputerDto;
import com.excilys.exception.validation.ValidationException;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.validation.CompanyValidation;
import com.excilys.validation.ComputerValidation;
import com.excilys.view.UpdateComputerView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton implementation of UpdateComputerController.
 */
public class UpdateComputerController {

  /** Singleton implementation of UpdateComputerController. */
  private static UpdateComputerController updateComputerControllerInstance = null;
  /** Computer Mapper. */
  private static ComputerMapper computerMapper = ComputerMapper.getInstance();
  /** Company Mapper. */
  private static CompanyMapper companyMapper = CompanyMapper.getInstance();
  /** View. */
  private static UpdateComputerView view = UpdateComputerView.getInstance();
  /** Computer Validation. */
  private static ComputerValidation computerValidation = ComputerValidation.getInstance();
  /** Company Validation. */
  private static CompanyValidation companyValidation = CompanyValidation.getInstance();
  /** ComputerService. */
  private static ComputerService computerService = ComputerService.getInstance();
  /** CompanyService. */
  private static CompanyService companyService = CompanyService.getInstance();
  /** Scanner. */
  private static Scanner scan = new Scanner(System.in);
  /** Logger. */
  private static Logger logger = LoggerFactory.getLogger(UpdateComputerController.class);

  /**
   * Singleton implementation of UpdateComputerController.
   */
  private UpdateComputerController() {
    ComputerDto computerDto = null;
    Computer computer = null;

    view.askForId();
    int id = scan.nextInt();
    computer = computerService.get(id).get();

    if (computer != null) {
      try {
        computerDto = (ComputerDto) computerMapper.entityToDto(computer);
     
        computerDto.setId(computerDto.getId());

        view.askForNewName(computerDto.getName());
        String nameInput = scan.next();
        computerValidation.validateName(nameInput);
        computerDto.setName(nameInput);

        view.askForNewIntroduced(computerDto.getIntroduced());
        String introducedInput = scan.next();
        computerValidation.validateIntroductionDate(introducedInput);
        Date introducedDate = null;
        if (introducedInput != null) {
          computerDto.setIntroduced(introducedInput);
          introducedDate = new SimpleDateFormat("yyyy-MM-dd").parse(introducedInput);
        }

        view.askForNewDiscontinued(computerDto.getDiscontinued());
        String discontinuedInput = scan.next();
        computerValidation.validateDiscontinuationDate(discontinuedInput);
        Date discontinuedDate = null;
        
        if (discontinuedInput != null) {
          computerDto.setDiscontinued(discontinuedInput);
          discontinuedDate = new SimpleDateFormat("yyyy-MM-dd").parse(discontinuedInput);
        }
        
        computerValidation.validatePrecedence(introducedDate, discontinuedDate);

        view.askForNewCompany(computerDto.getCompanyDto());
        long companyInput = (long) scan.nextInt();
        Optional<Company> company = companyService.get(companyInput);
        companyValidation.validateId(company.get().getId());
        
        CompanyDto companyDto = null;
        if (company.isPresent()) {
          companyDto = (CompanyDto) companyMapper.entityToDto(company.get());
          computerDto.setCompanyDto(companyDto);
        }

        computer = computerMapper.dtoToEntity(computerDto);

        scan.close();
      } catch (ValidationException validationException) {
        logger.warn(validationException.getMessage());
      } catch (ParseException parseException) {
        logger.warn(parseException.getMessage());
      }

      try {
        if (computer != null) {
          computerService.update(computer);
        }
      } catch (Exception e) {
        logger.warn(e.getMessage());
      }
    }
  }

  /**
   * Singleton implementation of UpdateComputerController.
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
