package com.excilys.view;

import org.slf4j.LoggerFactory;

import com.excilys.controller.MenuController;

/**
 * The Class CliMainView.
 */
public class CliMainView {

  /** The cli main view instance. */
  private static CliMainView cliMainViewInstance = null;

  /**
   * Instantiates a new cli main view.
   */
  private CliMainView() {
    LoggerFactory.getLogger(this.getClass()).info("Displaying main menu");

    System.out.println("-------------------\n " + "1 / List Computers\n " + "2 / List Companies\n "
        + "3 / Show Details\n " + "4 / Create a Computer\n " + "5 / Update a Computer\n "
        + "6 / Delete a Computer\n ");

    MenuController.getInstance().resolve();
  }

  /**
   * Gets the single instance of CliMainView.
   *
   * @return single instance of CliMainView
   */
  public static CliMainView getInstance() {
    if (cliMainViewInstance == null) {
      cliMainViewInstance = new CliMainView();
    }
    return cliMainViewInstance;
  }
}
