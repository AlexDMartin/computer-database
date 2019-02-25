package com.excilys.view;

import java.util.List;

import com.excilys.dao.model.Company;

/**
 * The Class ListCompanyView.
 */
public class ListCompanyView {

  /** The list company view instance. */
  private static ListCompanyView listCompanyViewInstance = null;

  /**
   * Instantiates a new list company view.
   */
  private ListCompanyView() {
  }

  /**
   * Gets the single instance of ListCompanyView.
   *
   * @return single instance of ListCompanyView
   */
  public static ListCompanyView getInstance() {
    if (listCompanyViewInstance == null) {
      listCompanyViewInstance = new ListCompanyView();
    }
    return listCompanyViewInstance;
  }

  /**
   * Render.
   *
   * @param itemList the item list
   */
  public void render(List<Company> itemList) {
    for (Company item : itemList) {
      System.out.println(item);
    }
  }
}
