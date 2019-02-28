package com.excilys.view;

import java.util.List;

import com.excilys.dto.CompanyDTO;

/**
 * View used to list all companies.
 */
public class ListCompanyView {

  /** Singleton implementation of ListCompanyView. */
  private static ListCompanyView listCompanyViewInstance = null;

  /**
   * Singleton implementation of ListCompanyView.
   */
  private ListCompanyView() {
  }

  /**
   * Singleton implementation of ListCompanyView.
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
   * Displays the view.
   *
   * @param A list of CompanyDTO
   */
  public void render(List<CompanyDTO> companyDTOList) {
    for (CompanyDTO companyDTO : companyDTOList) {
      System.out.println(companyDTO.getName() + " (" + companyDTO.getId() + ")");
    }
  }
}
