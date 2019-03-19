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
import com.excilys.view.CreateComputerView;
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
public class CreateComputerController {
  
  @Autowired
  private ComputerMapper computerMapper;
  @Autowired
  private CompanyMapper companyMapper;
  @Autowired
  private CreateComputerView view;
  @Autowired
  private ComputerService computerService;
  @Autowired
  private CompanyService companyService;
  private static Scanner scan = new Scanner(System.in);
  private static Logger logger = LoggerFactory.getLogger(CreateComputerController.class);

  private CreateComputerController() {}

  /**
   * Renders the Create Computer view.
   */
  public void render() {
    Computer computer = null;
    try {
      ComputerDtoBuilder computerDtoBuilder = new ComputerDtoBuilder();

      view.askForName();
      String nameInput = scan.next();
      computerDtoBuilder.addName(nameInput);

      view.askForIntroduced();
      String introducedInput = scan.next();
      Date introducedDate = null;
      if (introducedInput != null) {
        computerDtoBuilder.addIntroduced(introducedInput);
        introducedDate = new SimpleDateFormat("yyyy-MM-dd").parse(introducedInput);
      }

      view.askForDiscontinued();
      String discontinuedInput = scan.next();
      Date discontinuedDate = null;
      if (discontinuedInput != null) {
        computerDtoBuilder.addDiscontinued(discontinuedInput);
        discontinuedDate = new SimpleDateFormat("yyyy-MM-dd").parse(discontinuedInput);
      }

      view.askForCompany();
      long companyInput = (long) scan.nextInt();
      Optional<Company> company = companyService.get(companyInput);
      if (company.isPresent()) {
        CompanyDto companyDto = null;
        companyDto = (CompanyDto) companyMapper.entityToDto(company.get());
        computerDtoBuilder.addCompanyDto(companyDto);
      }
     
      computer = computerMapper.dtoToEntity(computerDtoBuilder.build());

      scan.close();
    } catch (ValidationException | ParseException e) {
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
}
