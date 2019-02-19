package com.excilys.controller;

import com.excilys.view.CliMainView;
import org.slf4j.LoggerFactory;

public class CliRenderer {

  public static void main(String[] args) {
    LoggerFactory.getLogger(CliRenderer.class).info("Main Started");

    CliMainView.getInstance();
  }

}
