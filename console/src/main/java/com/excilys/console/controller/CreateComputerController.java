package com.excilys.console.controller;

import com.excilys.console.view.CreateComputerView;
import com.excilys.dto.CompanyDto;
import com.excilys.dto.ComputerDto;
import com.excilys.dto.ComputerDtoBuilder;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.service.exception.ServiceException;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CreateComputerController {

  private static Scanner scan = new Scanner(System.in);

  private ComputerService computerService;
  private CompanyService companyService;
  private CreateComputerView view;

  @Autowired
  private CreateComputerController(ComputerService computerService, CompanyService companyService,
      CreateComputerView createComputerView) {
    this.computerService = computerService;
    this.companyService = companyService;
    this.view = createComputerView;
  }

  /**
   * Renders the Create Computer view.
   */
  public void render() {
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
      int companyInput = scan.nextInt();
      Optional<CompanyDto> company = companyService.find(companyInput);
      if (company.isPresent()) {
        CompanyDto companyDto = null;
        computerDtoBuilder.addCompanyDto(companyDto);
      }

      ComputerDto computerDto = computerDtoBuilder.build();
      computerService.save(computerDto);

      scan.close();
    } catch (ServiceException serviceException) {
      view.displayError();
    }

  }
}
