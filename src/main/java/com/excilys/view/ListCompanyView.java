package com.excilys.view;

import com.excilys.dto.CompanyDto;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ListCompanyView {

  private ListCompanyView() {}

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
