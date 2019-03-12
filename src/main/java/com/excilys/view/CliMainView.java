package com.excilys.view;

import com.excilys.controller.MenuController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CliMainView {
  @Autowired
  private MenuController menuController;
  private static final Logger logger = LoggerFactory.getLogger(CliMainView.class);

  private CliMainView() {}

  /**
   * Renders the cli main view.
   */
  public void render() {
    logger.info("Displaying main menu");

    System.out.println("-------------------\n " + "1 / List Computers\n " + "2 / List Companies\n "
        + "3 / Show Details\n " + "4 / Create a Computer\n " + "5 / Update a Computer\n "
        + "6 / Delete a Computer\n ");


    menuController.resolve();
  }

}
