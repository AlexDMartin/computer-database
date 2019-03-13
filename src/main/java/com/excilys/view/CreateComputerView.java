package com.excilys.view;

import org.springframework.stereotype.Component;

@Component
public class CreateComputerView {

  private CreateComputerView() {}

  /**
   * Ask for name.
   */
  public void askForName() {
    System.out.println("Enter name: ");
  }

  /**
   * Ask for introduced.
   */
  public void askForIntroduced() {
    System.out.println("Enter introduced date: ");
  }

  /**
   * Ask for discontinued.
   */
  public void askForDiscontinued() {
    System.out.println("Enter discontinued date: ");
  }

  /**
   * Ask for company.
   */
  public void askForCompany() {
    System.out.println("Enter company id: ");
  }
}
