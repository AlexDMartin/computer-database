package com.excilys.controller;

import java.util.Scanner;

import org.slf4j.LoggerFactory;

import com.excilys.dao.model.MenuItem;

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
    MenuItem inputMenuItem = MenuItem.valueOf(input).get();
    switch (inputMenuItem) {
    case LIST_COMPUTERS:
      ListComputerController.getInstance();
      break;
    case LIST_COMPANIES:
      ListCompanyController.getInstance();
      break;
    case SHOW_DETAILS:
      ShowDetailsController.getInstance();
      break;
    case CREATE_COMPUTER:
      CreateComputerController.getInstance();
      break;
    case UPDATE_COMPUTER:
      UpdateComputerController.getInstance();
      break;
    case DELETE_COMPUTER:
      DeleteComputerController.getInstance();
      break;
    default:
      break;
    }
    scan.close();
  }
}
