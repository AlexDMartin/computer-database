package com.excilys.view;

import com.excilys.dao.model.Computer;

/**
 * The Class DeleteComputerView.
 */
public class DeleteComputerView {

  /** The delete computer view instance. */
  private static DeleteComputerView deleteComputerViewInstance = null;

  /**
   * Instantiates a new delete computer view.
   */
  private DeleteComputerView() {}

  /**
   * Gets the single instance of DeleteComputerView.
   *
   * @return single instance of DeleteComputerView
   */
  public static DeleteComputerView getInstance() {
    if (deleteComputerViewInstance == null) {
      deleteComputerViewInstance = new DeleteComputerView();
    }
    return deleteComputerViewInstance;
  }

  /**
   * Ask for id.
   */
  public void askForId() {
    System.out.println("Enter the id of the computer you want to delete");
  }

  /**
   * Ask for confirmation.
   *
   * @param computer the computer
   */
  public void askForConfirmation(Computer computer) {
    System.out.println("Are you sure you want to delete: " + computer);
  }
}
