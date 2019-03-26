package com.excilys.dao.model;

import java.util.Arrays;
import java.util.Optional;

public enum MenuItem {
  LIST_COMPUTERS(1), LIST_COMPANIES(2), SHOW_DETAILS(3), CREATE_COMPUTER(4), UPDATE_COMPUTER(
      5), DELETE_COMPUTER(6);

  private int menuItemNumber;

  private MenuItem(int number) {
    this.menuItemNumber = number;
  }

  public static Optional<MenuItem> valueOf(int value) {
    return Arrays.stream(values()).filter(menuItem -> menuItem.menuItemNumber == value).findFirst();
  }

}
