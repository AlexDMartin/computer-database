package com.excilys.controller;

import com.excilys.dao.mappers.ComputerMapper;
import com.excilys.dao.model.Computer;
import com.excilys.dto.ComputerDto;
import com.excilys.exception.validation.computer.ComputerValidationException;
import com.excilys.service.ComputerService;
import com.excilys.view.ListComputerView;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ListComputerController {

  private Logger logger = LoggerFactory.getLogger(ListComputerController.class);

  private ComputerService computerService;
  private ComputerMapper computerMapper;
  private ListComputerView view;

  @Autowired
  private ListComputerController(ComputerService computerService, ComputerMapper computerMapper,
      ListComputerView listComputerView) {
    this.computerService = computerService;
    this.computerMapper = computerMapper;
    this.view = listComputerView;
  }

  /**
   * Renders the List Computer view.
   */
  public void render() {
    logger.info("List computers");

    List<Computer> computerList = computerService.getAll();
    List<ComputerDto> computerDtoList = new ArrayList<>();

    for (Computer computer : computerList) {
      try {
        computerDtoList.add((ComputerDto) computerMapper.entityToDto(computer));
      } catch (ComputerValidationException e) {
        logger.warn(e.getMessage());
      }
    }

    view.render(computerDtoList);

  }
}
