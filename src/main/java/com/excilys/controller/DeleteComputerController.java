package com.excilys.controller;

import com.excilys.dao.model.Computer;
import com.excilys.service.ComputerService;
import com.excilys.view.DeleteComputerView;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DeleteComputerController {

  private static Scanner scan = new Scanner(System.in);

  private ComputerService computerService;
  private DeleteComputerView view;

  @Autowired
  private DeleteComputerController(ComputerService computerService,
      DeleteComputerView deleteComputerView) {
    this.computerService = computerService;
    this.view = deleteComputerView;
  }

  /**
   * Renders the Delete Computer view.
   */
  public void render() {

    view.askForId();

    int id = scan.nextInt();

    Optional<Computer> computer = computerService.get(id);

    if (computer.isPresent()) {
      computerService.delete(computer.get());
    }

    scan.close();
  }
}
