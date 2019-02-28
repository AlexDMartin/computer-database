package com.excilys.controller;

import com.excilys.view.CliMainView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entrypoint for the CLI Application.
 */
public class CliRenderer {

  private static Logger logger = LoggerFactory.getLogger(CliRenderer.class);
  
  /**
   * Entrypoint method for the CLI Application.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    logger.info("Main Started");

    CliMainView.getInstance();
  }
}
