package com.excilys.console.controller;

import com.excilys.console.view.ListComputerView;
import com.excilys.dto.ComputerDto;
import com.excilys.service.ComputerService;
import com.excilys.service.exception.ServiceException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ListComputerController {

  private ComputerService computerService;
  private ListComputerView view;

  @Autowired
  private ListComputerController(ComputerService computerService,
      ListComputerView listComputerView) {
    this.computerService = computerService;
    this.view = listComputerView;
  }

  /**
   * Renders the List Computer view.
   */
  public void render() {

    List<ComputerDto> computers = new ArrayList<>();
    try {
      computers = computerService.findAll();
    } catch (ServiceException e) {
      view.displayError();
    }

    view.render(computers);

  }
}
