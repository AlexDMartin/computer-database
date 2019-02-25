package com.excilys.controller;

import java.util.Optional;
import java.util.Scanner;

import com.excilys.dao.model.Computer;
import com.excilys.service.ComputerService;
import com.excilys.view.ShowDetailsView;

/**
 * The Class ShowDetailsController.
 */
public class ShowDetailsController {

  /** The show details controller instance. */
  private static ShowDetailsController showDetailsControllerInstance = null;

  /**
   * Instantiates a new show details controller.
   */
  private ShowDetailsController() {
    ShowDetailsView view = ShowDetailsView.getInstance();
    view.askForId();

    Scanner scan = new Scanner(System.in);
    long id = (long) scan.nextInt();
    scan.close();

    Optional<Computer> computer = ComputerService.getInstance().get(id);
    view.displayComputer(computer);
  }

  /**
   * Gets the single instance of ShowDetailsController.
   *
   * @return single instance of ShowDetailsController
   */
  public static ShowDetailsController getInstance() {
    if (showDetailsControllerInstance == null) {
      showDetailsControllerInstance = new ShowDetailsController();
    }
    return showDetailsControllerInstance;
  }
}
