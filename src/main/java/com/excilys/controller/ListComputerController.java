package com.excilys.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.excilys.dao.mappers.ComputerMapper;
import com.excilys.dao.model.Computer;
import com.excilys.dto.ComputerDto;
import com.excilys.exception.validation.computer.ComputerValidationException;
import com.excilys.service.ComputerService;
import com.excilys.view.ListComputerView;

/**
 * Displays the Computer list.
 */
public class ListComputerController {

  /** Singleton implementation of ListCompanyController. */
  private static ListComputerController listComputerControllerInstance = null;
  /** Logger. */
  private static Logger logger = LoggerFactory.getLogger(ListComputerController.class);
  /** Company service. */
  private static ComputerService computerService = ComputerService.getInstance();
  /** Mapper. */
  private static ComputerMapper computerMapper = ComputerMapper.getInstance();
  /** View. */
  private static ListComputerView view = ListComputerView.getInstance();

  /**
   * Singleton implementation of ListCompanyController.
   */
  private ListComputerController() {
    logger.info("List computers");

    List<Computer> computerList = computerService.getAll();
    List<ComputerDto> computerDtoList = new ArrayList<ComputerDto>();

    for (Computer computer : computerList) {
      try {
        computerDtoList.add((ComputerDto) computerMapper.entityToDto(computer));
      } catch (ComputerValidationException e) {
        logger.warn(e.getMessage());
      }
    }

    view.render(computerDtoList);
  }

  /**
   * Singleton implementation of ListCompanyController.
   *
   * @return single instance of ListComputerController
   */
  public static ListComputerController getInstance() {
    if (listComputerControllerInstance == null) {
      listComputerControllerInstance = new ListComputerController();
    }
    return listComputerControllerInstance;
  }

}
