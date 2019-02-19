package com.excilys.controller;

import com.excilys.view.CliMainView;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class CliRenderer.
 */
public class CliRenderer {

  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    LoggerFactory.getLogger(CliRenderer.class).info("Main Started");

    CliMainView.getInstance();
  }

}
