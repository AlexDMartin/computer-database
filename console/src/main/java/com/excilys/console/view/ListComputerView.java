package com.excilys.console.view;

import com.excilys.dto.ComputerDto;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ListComputerView {

  private ListComputerView() {}

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

  public void displayError() {
    System.out.println("There was an error displaying the computers");
  }
}
