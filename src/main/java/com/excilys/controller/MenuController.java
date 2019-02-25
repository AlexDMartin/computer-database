package com.excilys.controller;

import java.util.Scanner;

import org.slf4j.LoggerFactory;

/**
 * The Class MenuController.
 */
public class MenuController {

  /** The menu controller instance. */
  private static MenuController menuControllerInstance = null;

  /**
   * Instantiates a new menu controller.
   */
  private MenuController() {
  }

  /**
   * Gets the single instance of MenuController.
   *
   * @return single instance of MenuController
   */
  public static MenuController getInstance() {
    if (menuControllerInstance == null) {
      menuControllerInstance = new MenuController();
    }
    return menuControllerInstance;
  }

  /**
   * Resolve.
   */
  public void resolve() {
    Scanner scan = new Scanner(System.in);
    int input = scan.nextInt();
    LoggerFactory.getLogger(this.getClass()).info("User typed : " + input);
    switch (input) {
    case 1:
      ListComputerController.getInstance();
      break;
    case 2:
      ListCompanyController.getInstance();
      break;
    case 3:
      ShowDetailsController.getInstance();
      break;
    case 4:
      CreateComputerController.getInstance();
      break;
    case 5:
      UpdateComputerController.getInstance();
      break;
    case 6:
      DeleteComputerController.getInstance();
      break;
    default:
      break;
    }
    scan.close();
  }
}
