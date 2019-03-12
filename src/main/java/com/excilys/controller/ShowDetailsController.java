package com.excilys.controller;

import com.excilys.dao.mappers.ComputerMapper;
import com.excilys.dao.model.Computer;
import com.excilys.dto.ComputerDto;
import com.excilys.exception.validation.computer.ComputerValidationException;
import com.excilys.service.ComputerService;
import com.excilys.view.ShowDetailsView;
import java.util.Optional;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ShowDetailsController {

  @Autowired
  private ComputerService computerService;
  @Autowired
  private ComputerMapper computerMapper;
  @Autowired
  private ShowDetailsView view;
  private static Scanner scan = new Scanner(System.in);
  private static Logger logger = LoggerFactory.getLogger(ShowDetailsController.class);

  private ShowDetailsController() {}

  /**
   * Renders the Show Details view.
   */
  public void render() {

    view.askForId();

    int id = scan.nextInt();
    scan.close();

    Optional<Computer> computer = computerService.get(id);

    if (computer.isPresent()) {
      ComputerDto computerDto = null;
      try {
        computerDto = (ComputerDto) computerMapper.entityToDto(computer.get());
      } catch (ComputerValidationException e) {
        logger.warn(e.getMessage());
      }
      view.displayComputer(computerDto);
    }
  }
}
