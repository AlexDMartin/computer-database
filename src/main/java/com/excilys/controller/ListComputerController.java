package com.excilys.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dao.mappers.ComputerMapper;
import com.excilys.dao.model.Computer;
import com.excilys.dto.ComputerDTO;
import com.excilys.service.ComputerService;
import com.excilys.view.ListComputerView;

/**
 * Displays the Computer list.
 */
public class ListComputerController {

  /** Singleton implementation of ListCompanyController. */
  private static ListComputerController listComputerControllerInstance = null;
  /** Logger */
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  /** Company service. */
  ComputerService computerService = ComputerService.getInstance();
  /** Mapper. */
  ComputerMapper computerMapper = ComputerMapper.getInstance();
  /** View */
  ListComputerView view = ListComputerView.getInstance();
  
  /**
   * Singleton implementation of ListCompanyController.
   */
  private ListComputerController() {
    logger.info("List computers");
    
    List<Computer> computerList = computerService.getAll();
    List<ComputerDTO> computerDTOList = new ArrayList<ComputerDTO>();
    
    for(Computer computer: computerList) {
      computerDTOList.add((ComputerDTO) this.computerMapper.entityToDTO(computer));
    }
    
    view.render(computerDTOList);
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
