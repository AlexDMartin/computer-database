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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UpdateComputerController {

  @Autowired
  private ComputerMapper computerMapper;
  @Autowired
  private CompanyMapper companyMapper;
  @Autowired
  private UpdateComputerView view;
  @Autowired
  private ComputerValidation computerValidation;
  @Autowired
  private CompanyValidation companyValidation;
  @Autowired
  private ComputerService computerService;
  @Autowired
  private CompanyService companyService;

  private static Scanner scan = new Scanner(System.in);
  private static Logger logger = LoggerFactory.getLogger(UpdateComputerController.class);

  private UpdateComputerController() {}

  /**
   * Renders the Update Computer view.
   */
  public void render() {

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
        computerDto.setName(nameInput);

        view.askForNewIntroduced(computerDto.getIntroduced());
        String introducedInput = scan.next();
        Date introducedDate = null;
        if (introducedInput != null) {
          computerDto.setIntroduced(introducedInput);
          introducedDate = new SimpleDateFormat("yyyy-MM-dd").parse(introducedInput);
        }

        view.askForNewDiscontinued(computerDto.getDiscontinued());
        String discontinuedInput = scan.next();

        Date discontinuedDate = null;
        if (discontinuedInput != null) {
          computerDto.setDiscontinued(discontinuedInput);
          discontinuedDate = new SimpleDateFormat("yyyy-MM-dd").parse(discontinuedInput);
        }

        view.askForNewCompany(computerDto.getCompanyDto());
        long companyInput = (long) scan.nextInt();
        Optional<Company> company = companyService.get(companyInput);

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
}
