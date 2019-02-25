package com.excilys.view;

/**
 * The Class CreateComputerView.
 */
public class CreateComputerView {

  /** The create computer view instance. */
  private static CreateComputerView createComputerViewInstance = null;

  /**
   * Instantiates a new creates the computer view.
   */
  private CreateComputerView() {
  }

  /**
   * Gets the single instance of CreateComputerView.
   *
   * @return single instance of CreateComputerView
   */
  public static CreateComputerView getInstance() {
    if (createComputerViewInstance == null) {
      createComputerViewInstance = new CreateComputerView();
    }
    return createComputerViewInstance;
  }

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
