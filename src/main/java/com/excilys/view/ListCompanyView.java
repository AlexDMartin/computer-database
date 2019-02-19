package com.excilys.view;

import java.util.List;

import com.excilys.dao.model.Company;

public class ListCompanyView {

  private static ListCompanyView listCompanyViewInstance = null;

  private ListCompanyView() {
  }

  public static ListCompanyView getInstance() {
    if (listCompanyViewInstance == null) {
      listCompanyViewInstance = new ListCompanyView();
    }
    return listCompanyViewInstance;
  }

  public void render(List<Company> itemList) {
    for (Company item : itemList) {
      System.out.println(item);
    }
  }
}
