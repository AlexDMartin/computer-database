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

/**
 * Singleton implementation of ShowDetailsController.
 */
public class ShowDetailsController {

  /** Singleton implementation of ShowDetailsController. */
  private static ShowDetailsController showDetailsControllerInstance = null;
  /** Logger. */
  private static Logger logger = LoggerFactory.getLogger(ShowDetailsController.class);
  /** ComputerService. */
  private static ComputerService computerService = ComputerService.getInstance();
  /** ComputerMapper. */
  private static ComputerMapper computerMapper = ComputerMapper.getInstance();
  /** View. */
  private static ShowDetailsView view = ShowDetailsView.getInstance();
  /** Scanner. */
  private static Scanner scan = new Scanner(System.in);

  /**
   * Singleton implementation of ShowDetailsController.
   */
  private ShowDetailsController() {

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

  /**
   * Gets the single instance of ShowDetailsController.
   *
   * @return single instance of ShowDetailsController
   */
  public static ShowDetailsController getInstance() {
    if (showDetailsControllerInstance == null) {
      showDetailsControllerInstance = new ShowDetailsController();
    }
    return showDetailsControllerInstance;
  }
}
