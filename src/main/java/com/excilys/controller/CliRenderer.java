package com.excilys.controller;

import com.excilys.config.SpringConfig;
import com.excilys.view.CliMainView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CliRenderer {

  private static Logger logger = LoggerFactory.getLogger(CliRenderer.class);

  /**
   * Entrypoint method for the CLI Application.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    try {
      ApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(SpringConfig.class);
      CliMainView cliMainView = applicationContext.getBean(CliMainView.class);
      cliMainView.render();
      logger.warn(applicationContext.toString());
      logger.info("Main Started");
    } catch (Exception e) {
      logger.warn(e.getMessage());
    }
  }
}
