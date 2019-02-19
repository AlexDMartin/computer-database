package com.excilys.controller;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Company;
import com.excilys.view.ListCompanyView;

public class ListCompanyController {

  private static ListCompanyController listCompanyControllerInstance = null;

  private ListCompanyController() {
    LoggerFactory.getLogger(this.getClass()).info("Listing Companies");
    List<Company> companyList = DaoFactory.getInstance().getCompanyDao().getAll();
    ListCompanyView.getInstance().render(companyList);
  }

  public static ListCompanyController getInstance() {
    if (listCompanyControllerInstance == null) {
      listCompanyControllerInstance = new ListCompanyController();
    }
    return listCompanyControllerInstance;
  }
}
