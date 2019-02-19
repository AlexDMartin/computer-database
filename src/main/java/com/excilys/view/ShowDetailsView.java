package com.excilys.view;

import java.util.Optional;

import com.excilys.dao.model.Computer;

public class ShowDetailsView {

  private static ShowDetailsView showDetailsViewInstance = null;

  private ShowDetailsView() {
  }

  public static ShowDetailsView getInstance() {
    if (showDetailsViewInstance == null) {
      showDetailsViewInstance = new ShowDetailsView();
    }
    return showDetailsViewInstance;
  }

  public void askForId() {
    System.out.println("Enter the id of the computer you want to display");
  }

  public void displayComputer(Optional<Computer> computer) {
    System.out.println(computer.get());
  }
}
