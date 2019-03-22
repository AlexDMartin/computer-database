package com.excilys.controller;

import com.excilys.dao.model.MenuItem;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MenuController {
  
  private ListComputerController listComputerController;
  private ListCompanyController listCompanyController;
  private ShowDetailsController showDetailsController;
  private CreateComputerController createComputerController;
  private UpdateComputerController updateComputerController;
  private DeleteComputerController deleteComputerController;

  @Autowired
  private MenuController(ListComputerController listComputerController,
      ListCompanyController listCompanyController, 
      ShowDetailsController showDetailsController,
      CreateComputerController createComputerController,
      UpdateComputerController updateComputerController,
      DeleteComputerController deleteComputerController) {
    this.listComputerController = listComputerController;
    this.listCompanyController = listCompanyController;
    this.showDetailsController = showDetailsController;
    this.createComputerController = createComputerController;
    this.updateComputerController = updateComputerController;
    this.deleteComputerController = deleteComputerController;
  }

  /**
   * Resolve.
   */
  public void resolve() {
    Scanner scan = new Scanner(System.in);
    int input = scan.nextInt();
    MenuItem inputMenuItem = MenuItem.valueOf(input).get();
    switch (inputMenuItem) {
      case LIST_COMPUTERS:
        listComputerController.render();
        break;
      case LIST_COMPANIES:
        listCompanyController.render();
        break;
      case SHOW_DETAILS:
        showDetailsController.render();
        break;
      case CREATE_COMPUTER:
        createComputerController.render();
        break;
      case UPDATE_COMPUTER:
        updateComputerController.render();
        break;
      case DELETE_COMPUTER:
        deleteComputerController.render();
        break;
      default:
        break;
    }
    scan.close();
  }
}
