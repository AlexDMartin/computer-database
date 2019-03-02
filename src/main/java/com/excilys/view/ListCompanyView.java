package com.excilys.view;

import com.excilys.dto.CompanyDto;
import java.util.List;

/**
 * View used to list all companies.
 */
public class ListCompanyView {

  /** Singleton implementation of ListCompanyView. */
  private static ListCompanyView listCompanyViewInstance = null;

  /**
   * Singleton implementation of ListCompanyView.
   */
  private ListCompanyView() {}

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
   * @param companyDtoList A list of CompanyDto
   */
  public void render(List<CompanyDto> companyDtoList) {
    for (CompanyDto companyDto : companyDtoList) {
      System.out.println(companyDto.getName() + " (" + companyDto.getId() + ")");
    }
  }
}
