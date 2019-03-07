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

/**
 * Displays the Company list.
 */
public class ListCompanyController {

  /** Singleton implementation of ListCompanyController. */
  private static ListCompanyController listCompanyControllerInstance = null;
  /** Logger. */
  private static Logger logger = LoggerFactory.getLogger(ListCompanyController.class);
  /** Company service. */
  private static CompanyService companyService = CompanyService.getInstance();
  /** Mapper. */
  private static CompanyMapper companyMapper = CompanyMapper.getInstance();
  /** View. */
  private static ListCompanyView view = ListCompanyView.getInstance();

  /**
   * Singleton implementation of ListCompanyController.
   */
  private ListCompanyController() {
    logger.info("List companies");

    List<Company> companyList = companyService.getAll();
    List<CompanyDto> companyDtoList = new ArrayList<CompanyDto>();

    for (Company company : companyList) {
      try {
        companyDtoList.add((CompanyDto) companyMapper.entityToDto(company));
      } catch (CompanyValidationException e) {
        logger.warn(e.getMessage());
      }
    }

    view.render(companyDtoList);
  }

  /**
   * Singleton implementation of ListCompanyController.
   *
   * @return single instance of ListCompanyController
   */
  public static ListCompanyController getInstance() {
    if (listCompanyControllerInstance == null) {
      listCompanyControllerInstance = new ListCompanyController();
    }
    return listCompanyControllerInstance;
  }
}
