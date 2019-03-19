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
import java.util.Optional;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CreateComputerController {

  private static Scanner scan = new Scanner(System.in);
  private static Logger logger = LoggerFactory.getLogger(CreateComputerController.class);

  private ComputerMapper computerMapper;
  private CompanyMapper companyMapper;
  private ComputerService computerService;
  private CompanyService companyService;
  private CreateComputerView view;

  @Autowired
  private CreateComputerController(ComputerService computerService, CompanyService companyService,
      ComputerMapper computerMapper, CompanyMapper companyMapper,
      CreateComputerView createComputerView) {
    this.computerService = computerService;
    this.companyService = companyService;
    this.computerMapper = computerMapper;
    this.companyMapper = companyMapper;
    this.view = createComputerView;
  }

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
      if (introducedInput != null) {
        computerDtoBuilder.addIntroduced(introducedInput);
      }

      view.askForDiscontinued();
      String discontinuedInput = scan.next();
      if (discontinuedInput != null) {
        computerDtoBuilder.addDiscontinued(discontinuedInput);
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
    } catch (ValidationException validationException) {
      logger.warn(validationException.getMessage());
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
