package com.excilys.view;

import com.excilys.dto.ComputerDto;
import java.util.List;

/**
 * View used to list all computers.
 */
public class ListComputerView {

  /** Singleton implementation of ListComputerView. */
  private static ListComputerView listComputerViewInstance = null;

  /**
   * Singleton implementation of ListComputerView.
   */
  private ListComputerView() {}

  /**
   * Singleton implementation of ListComputerView.
   *
   * @return single instance of ListComputerView
   */
  public static ListComputerView getInstance() {
    if (listComputerViewInstance == null) {
      listComputerViewInstance = new ListComputerView();
    }
    return listComputerViewInstance;
  }

  /**
   * Displays the view.
   *
   * @param computerDtoList A list of ComputerDTO
   */
  public void render(List<ComputerDto> computerDtoList) {
    for (ComputerDto computerDto : computerDtoList) {
      StringBuilder line = new StringBuilder();

      line.append(computerDto.getName() + " (" + computerDto.getId() + ") ");

      if (computerDto.getIntroduced() != null) {
        line.append(", introduced " + computerDto.getIntroduced());
      }

      if (computerDto.getDiscontinued() != null) {
        line.append(", discontinued " + computerDto.getDiscontinued());
      }

      if (computerDto.getCompanyDto() != null) {
        line.append(", by company " + computerDto.getCompanyDto().getName() + " ("
            + computerDto.getCompanyDto().getId() + ")");
      }

      System.out.println(line.toString());
    }
  }
}
