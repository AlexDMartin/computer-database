package com.excilys.controller;

import com.excilys.dao.model.Computer;
import com.excilys.service.ComputerService;
import com.excilys.view.DeleteComputerView;
import java.util.Optional;
import java.util.Scanner;

/**
 * Singleton implementation of DeleteComputerController.
 */
public class DeleteComputerController {

  /** Singleton implementation of DeleteComputerController. */
  private static DeleteComputerController deleteComputerControllerInstance = null;
  /** ComputerService. */
  private static ComputerService computerService = ComputerService.getInstance();
  /** View. */
  private static DeleteComputerView view = DeleteComputerView.getInstance();
  /** Scanner. */
  private static Scanner scan = new Scanner(System.in);

  /**
   * Singleton implementation of DeleteComputerController.
   */
  private DeleteComputerController() {

    view.askForId();

    int id = scan.nextInt();

    Optional<Computer> computer = computerService.get(id);

    if (computer.isPresent()) {
      ComputerService.getInstance().delete(computer.get());
    }

    scan.close();
  }

  /**
   * Singleton implementation of DeleteComputerController.
   *
   * @return single instance of DeleteComputerController
   */
  public static DeleteComputerController getInstance() {
    if (deleteComputerControllerInstance == null) {
      deleteComputerControllerInstance = new DeleteComputerController();
    }
    return deleteComputerControllerInstance;
  }
}
