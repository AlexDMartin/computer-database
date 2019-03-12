package com.excilys.view;

import com.excilys.dto.ComputerDto;
import org.springframework.stereotype.Component;

@Component
public class ShowDetailsView {

  private ShowDetailsView() {}

  /**
   * Ask for id.
   */
  public void askForId() {
    System.out.println("Enter the id of the computer you want to display :");
  }

  /**
   * Display computer.
   *
   * @param computerDto the computer's data object
   */
  public void displayComputer(ComputerDto computerDto) {
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

    System.out.println(line);
  }
}
