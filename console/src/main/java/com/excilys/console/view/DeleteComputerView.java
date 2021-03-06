package com.excilys.console.view;

import com.excilys.dao.model.Computer;
import org.springframework.stereotype.Component;

@Component
public class DeleteComputerView {

  private DeleteComputerView() {}

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

  /**
   * Displays the error.
   */
  public void displayError() {
    System.out.println("There was an error deleting your computer");    
  }
}
