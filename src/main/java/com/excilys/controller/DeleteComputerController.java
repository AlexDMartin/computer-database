package com.excilys.controller;

import java.util.Scanner;

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Computer;
import com.excilys.view.DeleteComputerView;

public class DeleteComputerController {
  private static DeleteComputerController deleteComputerControllerInstance = null;

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

  public static DeleteComputerController getInstance() {
    if (deleteComputerControllerInstance == null) {
      deleteComputerControllerInstance = new DeleteComputerController();
    }
    return deleteComputerControllerInstance;
  }
}
