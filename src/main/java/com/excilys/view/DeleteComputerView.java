package com.excilys.view;

import com.excilys.dao.model.Computer;

public class DeleteComputerView {

  private static DeleteComputerView deleteComputerViewInstance = null;

  private DeleteComputerView() {
  }

  public static DeleteComputerView getInstance() {
    if (deleteComputerViewInstance == null) {
      deleteComputerViewInstance = new DeleteComputerView();
    }
    return deleteComputerViewInstance;
  }

  public void askForId() {
    System.out.println("Enter the id of the computer you want to delete");
  }

  public void askForConfirmation(Computer computer) {
    System.out.println("Are you sure you want to delete: " + computer);
  }
}
