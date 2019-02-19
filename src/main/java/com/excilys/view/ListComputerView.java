package com.excilys.view;

import java.util.List;

import com.excilys.dao.model.Computer;

public class ListComputerView {

  private static ListComputerView listComputerViewInstance = null;

  private ListComputerView() {
  }

  public static ListComputerView getInstance() {
    if (listComputerViewInstance == null) {
      listComputerViewInstance = new ListComputerView();
    }
    return listComputerViewInstance;
  }

  public void render(List<Computer> itemList) {
    for (Computer item : itemList) {
      System.out.println(item);
    }
  }
}
