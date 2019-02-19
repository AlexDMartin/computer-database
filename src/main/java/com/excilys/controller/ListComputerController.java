package com.excilys.controller;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Computer;
import com.excilys.view.ListComputerView;

// TODO: Auto-generated Javadoc
/**
 * The Class ListComputerController.
 */
public class ListComputerController {

  /** The list computer controller instance. */
  private static ListComputerController listComputerControllerInstance = null;

  /**
   * Instantiates a new list computer controller.
   */
  private ListComputerController() {
    LoggerFactory.getLogger(this.getClass()).info("Listing Computers");
    List<Computer> computerList = DaoFactory.getInstance().getComputerDao().getAll();
    ListComputerView.getInstance().render(computerList);
  }

  /**
   * Gets the single instance of ListComputerController.
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
