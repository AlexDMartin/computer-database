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

  @Autowired
  private ComputerService computerService;
  @Autowired
  private DeleteComputerView view;
  private static Scanner scan = new Scanner(System.in);

  private DeleteComputerController() {}

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
