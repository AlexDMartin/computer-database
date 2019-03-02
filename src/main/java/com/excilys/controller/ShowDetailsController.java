package com.excilys.controller;

import java.util.Optional;
import java.util.Scanner;

import com.excilys.dao.mappers.ComputerMapper;
import com.excilys.dao.model.Computer;
import com.excilys.dto.ComputerDTO;
import com.excilys.service.ComputerService;
import com.excilys.view.ShowDetailsView;

/**
 * Singleton implementation of ShowDetailsController.
 */
public class ShowDetailsController {

  /** Singleton implementation of ShowDetailsController. */
  private static ShowDetailsController showDetailsControllerInstance = null;
  /** ComputerService. */
  ComputerService computerService = ComputerService.getInstance();
  /** ComputerMapper. */
  ComputerMapper computerMapper = ComputerMapper.getInstance();
  /** View. */
  ShowDetailsView view = ShowDetailsView.getInstance();
  /** Scanner. */
  Scanner scan = new Scanner(System.in);
  
  /**
   * Singleton implementation of ShowDetailsController.
   */
  private ShowDetailsController() {
    
    view.askForId();

    int id = scan.nextInt();
    scan.close();

    Optional<Computer> computer = computerService.get(id);
    
    if(computer.isPresent()) {   
    	ComputerDTO computerDTO = (ComputerDTO) computerMapper.entityToDTO(computer.get());
    	view.displayComputer(computerDTO);
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
