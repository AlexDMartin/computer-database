package com.excilys.view;

import java.util.List;

import com.excilys.dao.model.Computer;

// TODO: Auto-generated Javadoc
/**
 * The Class ListComputerView.
 */
public class ListComputerView {

  /** The list computer view instance. */
  private static ListComputerView listComputerViewInstance = null;

  /**
   * Instantiates a new list computer view.
   */
  private ListComputerView() {
  }

  /**
   * Gets the single instance of ListComputerView.
   *
   * @return single instance of ListComputerView
   */
  public static ListComputerView getInstance() {
    if (listComputerViewInstance == null) {
      listComputerViewInstance = new ListComputerView();
    }
    return listComputerViewInstance;
  }

  /**
   * Render.
   *
   * @param itemList the item list
   */
  public void render(List<Computer> itemList) {
    for (Computer item : itemList) {
      System.out.println(item);
    }
  }
}
