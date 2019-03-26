package com.excilys.console.controller;

import com.excilys.console.view.ShowDetailsView;
import com.excilys.dto.ComputerDto;
import com.excilys.service.ComputerService;
import com.excilys.service.exception.ServiceException;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ShowDetailsController {

  private static Scanner scan = new Scanner(System.in);

  private ComputerService computerService;
  private ShowDetailsView view;

  @Autowired
  private ShowDetailsController(ComputerService computerService, ShowDetailsView view) {
    this.computerService = computerService;
    this.view = view;
  }

  /**
   * Renders the Show Details view.
   */
  public void render() {

    view.askForId();

    int id = scan.nextInt();
    scan.close();

    Optional<ComputerDto> computer = Optional.empty();
    try {
      computer = computerService.find(id);
    } catch (ServiceException e) {
      view.displayError();
    }

    if (computer.isPresent()) {
      view.displayComputer(computer.get());
    }
  }
}
