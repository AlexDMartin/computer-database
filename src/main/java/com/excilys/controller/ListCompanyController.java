package com.excilys.controller;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Company;
import com.excilys.view.ListCompanyView;

// TODO: Auto-generated Javadoc
/**
 * The Class ListCompanyController.
 */
public class ListCompanyController {

  /** The list company controller instance. */
  private static ListCompanyController listCompanyControllerInstance = null;

  /**
   * Instantiates a new list company controller.
   */
  private ListCompanyController() {
    LoggerFactory.getLogger(this.getClass()).info("Listing Companies");
    List<Company> companyList = DaoFactory.getInstance().getCompanyDao().getAll();
    ListCompanyView.getInstance().render(companyList);
  }

  /**
   * Gets the single instance of ListCompanyController.
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
