package com.excilys.view;

import java.util.Optional;

import com.excilys.dao.model.Computer;

/**
 * The Class ShowDetailsView.
 */
public class ShowDetailsView {

  /** The show details view instance. */
  private static ShowDetailsView showDetailsViewInstance = null;

  /**
   * Instantiates a new show details view.
   */
  private ShowDetailsView() {
  }

  /**
   * Gets the single instance of ShowDetailsView.
   *
   * @return single instance of ShowDetailsView
   */
  public static ShowDetailsView getInstance() {
    if (showDetailsViewInstance == null) {
      showDetailsViewInstance = new ShowDetailsView();
    }
    return showDetailsViewInstance;
  }

  /**
   * Ask for id.
   */
  public void askForId() {
    System.out.println("Enter the id of the computer you want to display");
  }

  /**
   * Display computer.
   *
   * @param computer the computer
   */
  public void displayComputer(Optional<Computer> computer) {
    System.out.println(computer.get());
  }
}
