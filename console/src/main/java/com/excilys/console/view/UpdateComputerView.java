package com.excilys.console.view;

import com.excilys.dto.CompanyDto;
import org.springframework.stereotype.Component;

@Component
public class UpdateComputerView {

  private UpdateComputerView() {}

  /**
   * Ask for id.
   */
  public void askForId() {
    System.out.println("Enter the id of the computer you want to update :");
  }

  /**
   * Ask for new name.
   *
   * @param name The name of the computer
   */
  public void askForNewName(String name) {
    System.out.println("Enter new name (" + name + "): ");
  }

  /**
   * Ask for new introduced.
   *
   * @param introduced the computer's introduction date
   */
  public void askForNewIntroduced(String introduced) {
    System.out.println("Enter new introduced date (" + introduced + "): ");
  }

  /**
   * Ask for new discontinued.
   *
   * @param discontinued the computer's discontinuation date
   */
  public void askForNewDiscontinued(String discontinued) {
    System.out.println("Enter new discontinued date (" + discontinued + "): ");
  }

  /**
   * Ask for new company.
   *
   * @param companyDto the computer's company data object
   */
  public void askForNewCompany(CompanyDto companyDto) {
    System.out.println("Enter new company id (" + companyDto.getId() + "): ");
  }

  /**
   * Displays the company error.
   */
  public void displayCompanyError() {
    System.out.println("There was an error retrieving the company");
  }

  /**
   * Displays the error.
   */
  public void displayError() {
    System.out.println("There was an error updating the computer");
  }
}
