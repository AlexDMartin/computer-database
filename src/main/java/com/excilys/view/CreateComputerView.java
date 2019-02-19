package com.excilys.view;

public class CreateComputerView {

  private static CreateComputerView createComputerViewInstance = null;

  private CreateComputerView() {
  }

  public static CreateComputerView getInstance() {
    if (createComputerViewInstance == null) {
      createComputerViewInstance = new CreateComputerView();
    }
    return createComputerViewInstance;
  }

  public void askForName() {
    System.out.println("Enter name: ");
  }

  public void askForIntroduced() {
    System.out.println("Enter introduced date: ");
  }

  public void askForDiscontinued() {
    System.out.println("Enter discontinued date: ");
  }

  public void askForCompany() {
    System.out.println("Enter company id: ");
  }
}
