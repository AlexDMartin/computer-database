package com.excilys.console.controller;

import com.excilys.console.view.DeleteComputerView;
import com.excilys.dto.ComputerDto;
import com.excilys.service.ComputerService;
import com.excilys.service.exception.ServiceException;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.stereotype.Controller;

@Controller
public class DeleteComputerController {

  private static Scanner scan = new Scanner(System.in);

  private ComputerService computerService;
  private DeleteComputerView view;

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

    Optional<ComputerDto> computer = Optional.empty();
    try {
      computer = computerService.find(id);
    } catch (ServiceException e) {
      view.displayError();
    }

    if (computer.isPresent()) {
      try {
        computerService.delete(computer.get());
      } catch (ServiceException e) {
        view.displayError();
      }
    }

    scan.close();
  }
}
