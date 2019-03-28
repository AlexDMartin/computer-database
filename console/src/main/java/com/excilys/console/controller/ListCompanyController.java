package com.excilys.console.controller;

import com.excilys.console.view.ListCompanyView;
import com.excilys.dto.CompanyDto;
import com.excilys.service.CompanyService;
import com.excilys.service.exception.ServiceException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;

@Controller
public class ListCompanyController {

  private CompanyService companyService;
  private ListCompanyView view;

  private ListCompanyController(CompanyService companyService, ListCompanyView listCompanyView) {
    this.companyService = companyService;
    this.view = listCompanyView;
  }

  /**
   * Renders the List Company view.
   */
  public void render() {

    List<CompanyDto> companies = new ArrayList<>();
    try {
      companies = companyService.findAll();
    } catch (ServiceException e) {
      view.displayError();
    }
    view.render(companies);
  }
}
