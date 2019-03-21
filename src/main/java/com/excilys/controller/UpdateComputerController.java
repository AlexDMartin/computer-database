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
import com.excilys.view.UpdateComputerView;
import java.util.Optional;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UpdateComputerController {

  private static Scanner scan = new Scanner(System.in);
  private static Logger logger = LoggerFactory.getLogger(UpdateComputerController.class);

  private ComputerService computerService;
  private CompanyService companyService;
  private ComputerMapper computerMapper;
  private CompanyMapper companyMapper;
  private UpdateComputerView view;

  @Autowired
  private UpdateComputerController(ComputerService computerService, CompanyService companyService,
      ComputerMapper computerMapper, CompanyMapper companyMapper,
      UpdateComputerView updateComputerView) {
    this.computerService = computerService;
    this.companyService = companyService;
    this.computerMapper = computerMapper;
    this.companyMapper = companyMapper;
  }

  /**
   * Renders the Update Computer view.
   */
  public void render() {

    ComputerDto computerDto = null;
    Optional<Computer> computer = null;

    view.askForId();
    int id = scan.nextInt();
    computer = computerService.get(id);

    if (computer.isPresent()) {
      try {
        computerDto = (ComputerDto) computerMapper.entityToDto(computer.get());

        computerDto.setId(computerDto.getId());

        view.askForNewName(computerDto.getName());
        String nameInput = scan.next();
        computerDto.setName(nameInput);

        view.askForNewIntroduced(computerDto.getIntroduced());
        String introducedInput = scan.next();
        if (introducedInput != null) {
          computerDto.setIntroduced(introducedInput);
        }

        view.askForNewDiscontinued(computerDto.getDiscontinued());
        String discontinuedInput = scan.next();

        if (discontinuedInput != null) {
          computerDto.setDiscontinued(discontinuedInput);
        }

        view.askForNewCompany(computerDto.getCompanyDto());
        int companyInput = scan.nextInt();
        Optional<Company> company = companyService.get(companyInput);

        CompanyDto companyDto = null;
        if (company.isPresent()) {
          companyDto = (CompanyDto) companyMapper.entityToDto(company.get());
          computerDto.setCompanyDto(companyDto);
        }

        computer = Optional.of(computerMapper.dtoToEntity(computerDto));

        scan.close();
      } catch (ValidationException validationException) {
        logger.warn(validationException.getMessage());
      }
      try {
        computerService.update(computer.get());
      } catch (Exception e) {
        logger.warn(e.getMessage());
      }
    }
  }
}
