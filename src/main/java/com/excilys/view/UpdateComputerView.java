package com.excilys.view;

import com.excilys.dao.model.Computer;
import com.excilys.dto.CompanyDTO;

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
  public void askForNewName(String name) {
    System.out.println("Enter new name (" + name + "): ");
  }

  /**
   * Ask for new introduced.
   *
   * @param computer the computer
   */
  public void askForNewIntroduced(String introduced) {
    System.out.println("Enter new introduced date (" + introduced + "): ");
  }

  /**
   * Ask for new discontinued.
   *
   * @param computer the computer
   */
  public void askForNewDiscontinued(String discontinued) {
    System.out.println("Enter new discontinued date (" + discontinued + "): ");
  }

  /**
   * Ask for new company.
   *
   * @param computer the computer
   */
  public void askForNewCompany(CompanyDTO companyDTO) {
    System.out.println("Enter new company id (" + companyDTO.getId() + "): ");
  }
}
