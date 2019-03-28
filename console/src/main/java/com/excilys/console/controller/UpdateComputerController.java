package com.excilys.console.controller;

import com.excilys.console.view.UpdateComputerView;
import com.excilys.dto.CompanyDto;
import com.excilys.dto.ComputerDto;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.service.exception.ServiceException;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.stereotype.Controller;

@Controller
public class UpdateComputerController {

  private static Scanner scan = new Scanner(System.in);

  private ComputerService computerService;
  private CompanyService companyService;
  private UpdateComputerView view;

  private UpdateComputerController(ComputerService computerService, CompanyService companyService,
      UpdateComputerView updateComputerView) {
    this.computerService = computerService;
    this.companyService = companyService;
    this.view = updateComputerView;
  }

  /**
   * Renders the Update Computer view.
   */
  public void render() {

    view.askForId();
    int id = scan.nextInt();

    Optional<ComputerDto> computer = Optional.empty();
    
    try {
      computer = computerService.find(id);
    } catch (ServiceException e1) {
      view.displayError();
    }
    
    if (computer.isPresent()) {

      ComputerDto computerDto = computer.get();
      
      view.askForNewName(computer.get().getName());
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
      
      Optional<CompanyDto> company = Optional.empty();
      try {
        company = companyService.find(companyInput);
      } catch (ServiceException e) {
        view.displayCompanyError();
      }

      if (company.isPresent()) {
        computerDto.setCompanyDto(company.get());
      }


      scan.close();

      try {
        computerService.update(computerDto);
      } catch (ServiceException e) {
        view.displayError();
      }

    }
  }
}
