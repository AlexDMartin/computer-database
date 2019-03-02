package com.excilys.controller;

import com.excilys.dao.mappers.ComputerMapper;
import com.excilys.dao.model.Computer;
import com.excilys.dto.ComputerDto;
import com.excilys.service.ComputerService;
import com.excilys.view.ListComputerView;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Displays the Computer list.
 */
public class ListComputerController {

  /** Singleton implementation of ListCompanyController. */
  private static ListComputerController listComputerControllerInstance = null;
  /** Logger. */
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  /** Company service. */
  ComputerService computerService = ComputerService.getInstance();
  /** Mapper. */
  ComputerMapper computerMapper = ComputerMapper.getInstance();
  /** View. */
  ListComputerView view = ListComputerView.getInstance();

  /**
   * Singleton implementation of ListCompanyController.
   */
  private ListComputerController() {
    logger.info("List computers");

    List<Computer> computerList = computerService.getAll();
    List<ComputerDto> computerDtoList = new ArrayList<ComputerDto>();

    for (Computer computer : computerList) {
      computerDtoList.add((ComputerDto) this.computerMapper.entityToDto(computer));
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
