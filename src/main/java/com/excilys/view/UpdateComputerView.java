package com.excilys.view;

import com.excilys.dao.model.Computer;

/**
 * The Class UpdateComputerView.
 */
public class UpdateComputerView {

  /** The update computer view instance. */
  private static UpdateComputerView updateComputerViewInstance = null;

  /**
   * Instantiates a new update computer view.
   */
  private UpdateComputerView() {
  }

  /**
   * Gets the single instance of UpdateComputerView.
   *
   * @return single instance of UpdateComputerView
   */
  public static UpdateComputerView getInstance() {
    if (updateComputerViewInstance == null) {
      updateComputerViewInstance = new UpdateComputerView();
    }
    return updateComputerViewInstance;
  }

  /**
   * Ask for id.
   */
  public void askForId() {
    System.out.println("Enter the id of the computer you want to update :");
  }

  /**
   * Ask for new name.
   *
   * @param computer the computer
   */
  public void askForNewName(Computer computer) {
    System.out.println("Enter new name (" + computer.getName() + "): ");
  }

  /**
   * Ask for new introduced.
   *
   * @param computer the computer
   */
  public void askForNewIntroduced(Computer computer) {
    System.out.println("Enter new introduced date (" + computer.getIntroduced() + "): ");
  }

  /**
   * Ask for new discontinued.
   *
   * @param computer the computer
   */
  public void askForNewDiscontinued(Computer computer) {
    System.out.println("Enter new discontinued date (" + computer.getDiscontinued() + "): ");
  }

  /**
   * Ask for new company.
   *
   * @param computer the computer
   */
  public void askForNewCompany(Computer computer) {
    System.out.println("Enter new company id (" + computer.getCompany() + "): ");
  }
}
