package com.excilys.controller;

import java.util.Scanner;

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Computer;
import com.excilys.validator.Validator;
import com.excilys.view.DeleteComputerView;

// TODO: Auto-generated Javadoc
/**
 * The Class DeleteComputerController.
 */
public class DeleteComputerController {

  /** The delete computer controller instance. */
  private static DeleteComputerController deleteComputerControllerInstance = null;

  /**
   * Instantiates a new delete computer controller.
   */
  private DeleteComputerController() {
    DeleteComputerView view = DeleteComputerView.getInstance();
    Validator validator = Validator.getInstance();

    view.askForId();

    Scanner scan = new Scanner(System.in);
    long id = (long) scan.nextInt();

    Computer computer = DaoFactory.getInstance().getComputerDao().get(id).get();

    view.askForConfirmation(computer);
    String confirmationInput = scan.next();

    if (validator.isYes(confirmationInput)) {
      DaoFactory.getInstance().getComputerDao().delete(computer);
    }

    scan.close();
  }

  /**
   * Gets the single instance of DeleteComputerController.
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
