package com.excilys.controller;

import com.excilys.dao.mappers.CompanyMapper;
import com.excilys.dao.model.Company;
import com.excilys.dto.CompanyDto;
import com.excilys.exception.validation.company.CompanyValidationException;
import com.excilys.service.CompanyService;
import com.excilys.view.ListCompanyView;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ListCompanyController {

  private Logger logger = LoggerFactory.getLogger(ListCompanyController.class);

  private CompanyService companyService;
  private CompanyMapper companyMapper;
  private ListCompanyView view;

  @Autowired
  private ListCompanyController(CompanyService companyService, CompanyMapper companyMapper,
      ListCompanyView listCompanyView) {
    this.companyService = companyService;
    this.companyMapper = companyMapper;
    this.view = listCompanyView;
  }

  /**
   * Renders the List Company view.
   */
  public void render() {
    logger.info("List companies");

    List<Company> companyList = companyService.getAll();
    List<CompanyDto> companyDtoList = new ArrayList<>();

    for (Company company : companyList) {
      try {
        companyDtoList.add((CompanyDto) companyMapper.entityToDto(company));
      } catch (CompanyValidationException e) {
        logger.warn(e.getMessage());
      }
    }

    view.render(companyDtoList);
  }
}
